package common.helper.storage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileStorage implements Storage {
    @Override
    public boolean save(String fileName, String contents) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.println(contents);

            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
