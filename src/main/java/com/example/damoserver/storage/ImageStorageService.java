package com.example.damoserver.storage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@Getter
public class ImageStorageService {

    public ImageStorageService(@Value("${spring.storage.path}") String storagePath) {
        this.storagePath = Paths.get(storagePath).normalize().toAbsolutePath();
        this.imageStoragePath = Paths.get("images");
    }

    private final Path storagePath; // 절대 경로
    private final Path imageStoragePath; // 상대 경로

    @Value("${spring.storage.path}/default.png")
    private String defaultProfileImageUrl;

    public Path storeImage(Path path, MultipartFile image) {
        validateImage(image);
        String randomizedFilename = ImageUtil.generateRandomFilename(image.getOriginalFilename());
        Path destination = resolveDestinationPath(path, randomizedFilename);
        copyImageToDestination(image, destination);
        return storagePath.relativize(destination);
    }

    public void deleteImage(Path path) {
        try {
            Files.delete(this.storagePath.resolve(path));
        } catch (IOException exception) {
            throw new RuntimeException("Could not delete image " + path, exception);
        }
    }

    private void validateImage(MultipartFile image) {
        try {
            if (image.isEmpty()) {
                throw new RuntimeException("Image is empty");
            }
            if (!ImageUtil.isValidImage(image.getInputStream())) {
                throw new RuntimeException("Image is not valid");
            }
        } catch (IOException exception) {
            log.debug("image validation failed: {}", exception.getMessage());
            throw new RuntimeException("Image validation failed", exception);
        }
    }

    private Path resolveDestinationPath(Path path, String filename) {
        return storagePath.resolve(imageStoragePath)
                .resolve(path)
                .resolve(filename);
    }

    private void copyImageToDestination(MultipartFile image, Path destination) {
        try (InputStream inputStream = image.getInputStream()) {
            makeDirectories(destination.getParent());
            Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new RuntimeException("Could not copy image " + image, exception);
        }
    }

    private void makeDirectories(Path path) {
        File directory = new File(path.toUri());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Could not create directory " + directory);
            }
        }
    }
}