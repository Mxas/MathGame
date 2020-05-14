package lt.mk.mathgame.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import lt.mk.mathgame.utils.RandomUtils;

public class PicService {

    public static final String DEFAULT_CORRECT = "pic/yes/";
    public static final String DEFAULT_BAD = "pic/no/";

    private List<String> defaultCorrect;
    private List<String> userPics = new ArrayList<>();
    private List<String> defaultIncorrect;
    private String imagesPath;

    public PicService(String imagesPath) {
        this.imagesPath = imagesPath;

        initDefaultCorrects();
        initByPath();
    }

    private void initDefaultCorrects() {
        try {

            defaultCorrect = listFromClasspath(DEFAULT_CORRECT);
            defaultIncorrect = listFromClasspath(DEFAULT_BAD);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String nextCorrect() {
        return DEFAULT_CORRECT + defaultCorrect.get(RandomUtils.next(defaultCorrect.size()));
    }

    private String nextIncorrect() {
        return DEFAULT_BAD + defaultIncorrect.get(RandomUtils.next(defaultIncorrect.size()));
    }

    public Optional<InputStream> nextCorrectIS() {
        try {
            if (!userPics.isEmpty() && RandomUtils.next(5) != 4) {
                return Optional.of(new FileInputStream(new File(userPics.get(RandomUtils.next(userPics.size())))));
            }
            return Optional.ofNullable(getContextClassLoader().getResourceAsStream(nextCorrect()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<InputStream> nextIncorrectIS() {
        try {
            return Optional.ofNullable(getContextClassLoader().getResourceAsStream(nextIncorrect()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    private List<String> listFromClasspath(String path) throws URISyntaxException, IOException {
        List<String> rez = new ArrayList<>();

        URI uri = getContextClassLoader().getResource(path).toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                myPath = fileSystem.getPath(path);
                fillFiles(rez, myPath);
            }
        } else {
            myPath = Paths.get(uri);
            fillFiles(rez, myPath);
        }

        return rez;
    }

    private void fillFiles(List<String> rez, Path myPath) throws IOException {
        Stream<Path> walk = Files.walk(myPath, 1);
        for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
            Path a = it.next();
            if (!Files.isDirectory(a)) {
                rez.add(a.getFileName().toString());
            }
        }
    }

    public void initByPath() {
        try {
            userPics = new ArrayList<>();

            if (this.imagesPath != null) {

                Path root = Paths.get(this.imagesPath.trim());

                if (Files.isDirectory(root)) {
                    Files.newDirectoryStream(Paths.get(this.imagesPath.trim())).forEach(path -> addIntoPath(userPics, path));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addIntoPath(List<String> paths, Path path) {
        if (paths.size() > 10000) {
            return;
        }
        if (Files.isDirectory(path)) {
            try {
                Files.list(path).forEach(p1 -> addIntoPath(paths, p1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (path.toString().toLowerCase().endsWith(".jpg") || path.toString().toLowerCase().endsWith(".jpeg")) {
            if (paths.size() > 10000) {
                return;
            }
            paths.add(path.toString());
        }
    }
}
