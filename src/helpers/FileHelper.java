package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

    public static List<String> readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        return Files.readAllLines(path);
    }

}
