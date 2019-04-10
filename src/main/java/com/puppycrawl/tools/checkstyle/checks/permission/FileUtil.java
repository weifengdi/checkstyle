package com.puppycrawl.tools.checkstyle.checks.permission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public static void saveStringToFile(String content, File targetFile) {

        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(targetFile);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
