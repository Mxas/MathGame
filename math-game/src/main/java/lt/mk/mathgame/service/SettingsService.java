package lt.mk.mathgame.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lt.mk.mathgame.model.PlayResult;
import lt.mk.mathgame.model.PlaySettings;

public class SettingsService {

    private static final String DELIMITER = ";";
    private static final String DEFAULT_SETTINGS = "10;10;20;1;1;0;0;";

    public SettingsService() {
        saveIfNeededDefaults();
    }

    public PlaySettings loadSettings() {
        try {
            return loadFromFileSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getDefaultSettings();
    }

    private PlaySettings loadFromFileSettings() throws IOException {

        String loadFromFIle = readSettings();
        if (loadFromFIle != null && loadFromFIle.trim().length() > 0) {
            String[] data = loadFromFIle.split(DELIMITER);
            if (data.length >= 7) {
                PlaySettings playSettings = new PlaySettings();
                playSettings.setMaxFirst(Integer.parseInt(data[0].trim()));
                playSettings.setMaxSecond(Integer.parseInt(data[1].trim()));
                playSettings.setMaxTotal(Integer.parseInt(data[2].trim()));
                playSettings.setUserPlus(Integer.parseInt(data[3].trim()) == 1);
                playSettings.setUserMinus(Integer.parseInt(data[4].trim()) == 1);
                playSettings.setUserMulti(Integer.parseInt(data[5].trim()) == 1);
                playSettings.setUserDiv(Integer.parseInt(data[6].trim()) == 1);
                if (data.length >= 8) {
                    playSettings.setImagesPath(data[7]);
                }
                return playSettings;
            }
        }
        return getDefaultSettings();

    }

    public PlayResult loadValues() {

        PlayResult r = new PlayResult();

        try {
            String values = readValues();
            if (values != null && values.trim().length() > 0) {
                String[] data = values.split(DELIMITER);
                if (data.length >= 2) {
                    r.setTotal(Integer.parseInt(data[0].trim()));
                    r.setCorrect(Integer.parseInt(data[1].trim()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    private String readSettings() throws IOException {
        File file = new File(getUserSettingsPath());
        if (file.isFile()) {
            return new String(Files.readAllBytes(file.toPath()));
        }
        return null;
    }

    private String readValues() throws IOException {
        File file = new File(getUserValuesPath());
        if (file.isFile()) {
            return new String(Files.readAllBytes(file.toPath()));
        }
        return null;
    }

    private String getUserDirectoryPath() {
        return System.getProperty("user.home") + File.separator + ".MathGame";
    }

    private String getUserSettingsPath() {
        return getUserDirectoryPath() + File.separator + "math-settings.dat";
    }

    private String getUserValuesPath() {
        return getUserDirectoryPath() + File.separator + "math-values.dat";
    }

    private PlaySettings getDefaultSettings() {
        PlaySettings playSettings = new PlaySettings();
        playSettings.setMaxFirst(15);
        playSettings.setMaxSecond(15);
        playSettings.setMaxTotal(20);
        playSettings.setUserPlus(true);
        playSettings.setUserMinus(true);
        return playSettings;
    }

    public void persistValues(int total, int correct) {
        try {
            checkGameDirectoryExist();
            String content = total + DELIMITER + correct;
            Files.write(Paths.get(getUserValuesPath()), content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkGameDirectoryExist() {
        File file = new File(getUserDirectoryPath());
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void saveIfNeededDefaults() {
        try {
            checkGameDirectoryExist();
            Path path = Paths.get(getUserSettingsPath());
            if (!Files.isReadable(path)) {
                Files.write(path, DEFAULT_SETTINGS.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
