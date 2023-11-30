package com.sosadwaden.service;

import com.sosadwaden.serviceUtil.ServicePropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = ServicePropertiesUtil.get("image.base.url");

    public void upload(String imagePath, InputStream imageContent) {
        Path imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<InputStream> get(String imagePath) {
        Path imageFullPath = Path.of(basePath, imagePath);
        try {
            return Files.exists(imageFullPath)
                    ? Optional.of(Files.newInputStream(imageFullPath))
                    : Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
