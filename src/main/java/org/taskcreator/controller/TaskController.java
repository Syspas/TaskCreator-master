package org.taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.taskcreator.model.Task;
import org.taskcreator.service.TaskService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/create-task")
    public String showCreateTaskForm() {
        return "create-task";
    }

    @PostMapping("/create-task")
    public String createTask(@RequestParam("task-title") String title,
                             @RequestParam("task-description") String description,
                             @RequestParam("task-files") MultipartFile[] files,
                             Model model) throws IOException {

        List<String> fileNames = Arrays.stream(files)
                .filter(file -> !file.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .toList();

        if (!fileNames.isEmpty()) {
            saveFiles(files);
        }

        Task task = taskService.createTask(title, description, fileNames);

        model.addAttribute("task", task);
        return "redirect:/task/" + task.getId();
    }

    @GetMapping("/task/{taskId}")
    public String showTask(@PathVariable Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            model.addAttribute("task", task);
            return "task";
        } else {
            return "redirect:/error"; // Добавьте страницу ошибки "error"
        }
    }

    // ... методы для редактирования и удаления (не показаны)

    private void saveFiles(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Path uploadDir = Paths.get("uploads");
                Files.createDirectories(uploadDir);

                Path filePath = uploadDir.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath);
            }
        }
    }


    @GetMapping("/getAllTasks")
    public String showTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }


}
