package org.taskcreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Контроллер для загрузки файлов.
 * Обрабатывает запросы на сохранение файлов, связанных с задачами.
 */
@Controller
public class FileUploadController {

    /**
     * Сохраняет загруженные файлы в директорию.
     *
     * @param files массив файлов, которые нужно сохранить
     * @throws IOException если произошла ошибка при сохранении файлов
     */
    @PostMapping("/upload-files")
    public void saveFiles(MultipartFile[] files) throws IOException {
        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir); // Создаём директорию, если её нет

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Path filePath = uploadDir.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath);
            }
        }
    }
}
