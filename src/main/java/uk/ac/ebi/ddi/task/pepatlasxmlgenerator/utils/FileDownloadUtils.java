package uk.ac.ebi.ddi.task.pepatlasxmlgenerator.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloadUtils {

    private FileDownloadUtils() {
    }

    public static void downloadFile(String fileUrl, File dest) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        try (InputStream is = connection.getInputStream();
             FileOutputStream fileOutput = new FileOutputStream(dest)) {

            byte[] buffer = new byte[2048];
            int bufferLength; //used to store a temporary size of the buffer

            while ((bufferLength = is.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
        }
    }
}
