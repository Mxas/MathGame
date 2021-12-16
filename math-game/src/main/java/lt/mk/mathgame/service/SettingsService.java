package lt.mk.mathgame.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import lt.mk.mathgame.model.Operation;
import lt.mk.mathgame.model.OperationSetting;
import lt.mk.mathgame.model.PlayResult;
import lt.mk.mathgame.model.PlaySettings;

public class SettingsService {

    private static final String DELIMITER = ";";
    private static final String DEFAULT_SETTINGS = "10;10;20;1\n"
            + "10;10;20;1\n"
            + "10;10;50;0\n"
            + "10;10;60;0\n";

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

        List<String> loadFromFile = readSettings();
        if (loadFromFile != null && !loadFromFile.isEmpty()) {
            PlaySettings playSettings = new PlaySettings();
            playSettings.setDefaults();
            for (int i = 0; i < Operation.values().length; i++) {
                if (loadFromFile.size() > i) {
                    String line = loadFromFile.get(i);
                    if (line != null && !line.trim().isEmpty()) {
                        String[] data = line.trim().split(DELIMITER);
                        if (data.length >= 4) {
                            playSettings.getValues().put(Operation.values()[i], new OperationSetting(
                                    Integer.parseInt(data[0].trim()),
                                    Integer.parseInt(data[1].trim()),
                                    Integer.parseInt(data[2].trim()),
                                    Integer.parseInt(data[3].trim()) == 1
                            ));
                        }
                    }
                }
            }
            if (loadFromFile.size() >= 5) {
                playSettings.setImagesPath(loadFromFile.get(4));
            }
            return playSettings;
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

    private List<String> readSettings() throws IOException {
        File file = new File(getUserSettingsPath());
        if (file.isFile()) {
            return Files.readAllLines(file.toPath());
        }
        return Collections.emptyList();
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
        playSettings.setDefaults();
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
            if (!Files.isReadable(path) || readSettings().size() < 4) {
                Files.write(path, DEFAULT_SETTINGS.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
