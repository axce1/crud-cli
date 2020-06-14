package utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IOUtils {
    public static RandomAccessFile getFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile raf
                = new RandomAccessFile(file, "rw");
        return raf;
    }

    public static Long getNextId(Long Id) {
        return ++Id;
    }
}
