package org.taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.taskcreator.model.Task;
import org.taskcreator.service.TaskService; // Импорт класса TaskService

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService; // Предположим, у вас есть сервис для работы с задачами

    @GetMapping("/create-task")
    public String showCreateTaskForm() {
        return "create-task"; // Возвращает HTML-шаблон, который вы уже предоставили
    }

    @PostMapping("/create-task")
    public String createTask(@RequestParam("task-title") String title,
                             @RequestParam("task-description") String description,
                             @RequestParam("task-files") MultipartFile[] files,
                             Model model) throws IOException {

        // Проверка, есть ли файлы, которые нужно загрузить
        List<String> fileNames = Arrays.stream(files)
                .filter(file -> !file.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .toList();

        // Сохранение файлов (если есть)
        if (!fileNames.isEmpty()) {
            saveFiles(files);
        }

        // Создание задачи
        Task task = taskService.createTask(title, description, fileNames);

        // Перенаправление на страницу с созданной задачей
        model.addAttribute("task", task);
        return "redirect:/task/" + task.getId(); // Предположим, что у вас есть контроллер для отображения задачи
    }

    private void saveFiles(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Path uploadDir = Paths.get("uploads"); // Путь для загрузки файлов
                Files.createDirectories(uploadDir); // Создание директории, если ее нет

                Path filePath = uploadDir.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath);
            }
        }
    }
}
