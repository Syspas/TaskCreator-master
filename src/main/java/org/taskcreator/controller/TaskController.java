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
import org.taskcreator.service.jira.api.client.post.JiraIssueCreator;


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
     * Конструктор контроллера.
     *
     * @param taskService служба для управления задачами
     */
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
        // Загружаем тестовые данные при инициализации контроллера
        taskService.loadTestData();
    }

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
     * Этот метод принимает заголовок, описание и файлы, связанные с задачей.
     * Если заголовок или описание пустые, возвращает на форму создания задачи с сообщением об ошибке.
     * Если задача успешно создана, редиректит на страницу с сообщением об успешном создании задачи.
     *
     * @param title       заголовок задачи
     * @param description описание задачи
     * @param files       массив файлов, связанных с задачей
     * @param model       модель для передачи данных в шаблон
     * @return редирект на страницу с сообщением об успешном создании задачи или возвращение на форму с ошибкой
     */
    @PostMapping("/create-task")
    public String createTask(@RequestParam("task-title") String title,
                             @RequestParam("task-description") String description,
                             @RequestParam("task-files") MultipartFile[] files,
                             Model model) {
        // Проверка на наличие заголовка и описания
        if (title.isEmpty() || description.isEmpty()) {
            model.addAttribute("error", "Заголовок и описание не могут быть пустыми.");
            return "create-task"; // Возвращаем на форму с ошибкой
        }

        // Получаем имена файлов и сохраняем их
        List<String> fileNames = Arrays.stream(files)
                .filter(file -> !file.isEmpty()) // Фильтруем пустые файлы
                .map(MultipartFile::getOriginalFilename)
                .toList();

        // Создаем задачу локально
        Task task = taskService.createTask(title, description, fileNames);

        // Создание экземпляра JiraIssueCreator
        JiraIssueCreator jiraIssueCreator;
        try {
            jiraIssueCreator = new JiraIssueCreator(); // Загружаем конфигурацию из файла
            String projectKey = "KAN"; // Замените на ваш ключ проекта
            String issueTypeId = "10001"; // ID типа задачи

            // Вызов метода для создания задачи в Jira с переданным issueTypeId
            jiraIssueCreator.createIssue(projectKey, title, description, issueTypeId);
            // Закрываем соединение с Jira
            //jiraIssueCreator.close();

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ошибка при создании задачи в Jira.");
            return "create-task"; // Возвращаем на форму с ошибкой
        }

        // Редирект на страницу с сообщением об успешном создании задачи
        return "redirect:/task-created/" + task.getId(); // Перенаправление на страницу с номером задачи
    }

    /**
     * Отображает задачу по указанному идентификатору.
     *
     * @param taskId идентификатор задачи
     * @param model  модель для передачи данных в шаблон
     * @return имя шаблона для отображения задачи или редирект на страницу ошибки
     */
    @GetMapping("/task/{taskId}")
    public String showTask(@PathVariable Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            model.addAttribute("task", task);
            return "task"; // Отображение общего представления задачи
        } else {
            System.err.println("Task not found for ID: " + taskId);
            return "redirect:/error"; // Добавьте страницу ошибки "error"
        }
    }

    /**
     * Отображает страницу с сообщением о создании задачи.
     *
     * @param taskId идентификатор созданной задачи
     * @param model  модель для передачи данных в шаблон
     * @return имя шаблона для отображения сообщения о созданной задаче
     */
    @GetMapping("/task-created/{taskId}")
    public String taskCreated(@PathVariable Long taskId, Model model) {
        model.addAttribute("taskId", taskId); // Передаем номер задачи в модель
        return "task-created"; // Возвращаем имя шаблона для отображения
    }
}
