package com.example.damoserver.storage;

import org.apache.tika.Tika;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class ImageUtil {
    private static final Tika tika = new Tika();

    private static final List<String> imageExtensions = List.of("jpg", "jpeg", "png", "gif");

    public static boolean isValidImage(InputStream inputStream) {
        try {
            String extension = tika.detect(inputStream);
            return imageExtensions.stream()
                    .anyMatch(notValidTye -> notValidTye.equalsIgnoreCase(extension));
        } catch (IOException exception) {
            return false;
        }
    }

    public static String generateRandomFilename(String filename) {
        String extension = StringUtils.getFilenameExtension(filename);
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String randomFilename = uuidString;
        return String.format("%s.%s", randomFilename, extension);
    }

}