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
import java.util.Arrays;
import java.util.List;

/**
 * Контроллер для управления задачами.
 * Обрабатывает запросы на создание и отображение задач.
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Отображает форму для создания новой задачи.
     *
     * @return имя шаблона для создания задачи
     */
    @GetMapping("/create-task")
    public String showCreateTaskForm() {
        return "create-task";
    }

    /**
     * Обрабатывает создание новой задачи.
     *
     * @param title заголовок задачи
     * @param description описание задачи
     * @param files массив файлов, связанных с задачей
     * @param model модель для передачи данных в шаблон
     * @return редирект на страницу задачи
     * @throws IOException если произошла ошибка при загрузке файлов
     */
    @PostMapping("/create-task")
    public String createTask(@RequestParam("task-title") String title,
                             @RequestParam("task-description") String description,
                             @RequestParam("task-files") MultipartFile[] files,
                             Model model) throws IOException {

        // Получаем имена файлов и сохраняем их
        List<String> fileNames = Arrays.stream(files)
                .filter(file -> !file.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .toList();

        // Создаём задачу
        Task task = taskService.createTask(title, description, fileNames);
        model.addAttribute("task", task);
        return "redirect:/task/" + task.getId();
    }

    /**
     * Отображает задачу по указанному идентификатору.
     *
     * @param taskId идентификатор задачи
     * @param model модель для передачи данных в шаблон
     * @return имя шаблона для отображения задачи или редирект на страницу ошибки
     */
    @GetMapping("/task/{taskId}")
    public String showTask(@PathVariable Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            model.addAttribute("task", task);
            return "task"; // Отображение общего представления задачи
        } else {
            // Логируем и перенаправляем на страницу ошибки
            System.err.println("Task not found for ID: " + taskId);
            return "redirect:/error"; // Добавьте страницу ошибки "error"
        }
    }
}
