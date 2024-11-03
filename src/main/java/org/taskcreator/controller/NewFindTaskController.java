package org.taskcreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.taskcreator.model.FindTask;
import  org.taskcreator.service.jira.api.client.get.JsonDownloader; // Предполагается, что у вас есть сервис JsonDownloader
import org.taskcreator.service.jira.api.client.get.date.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов на отображение информации о задачах.
 * Управляет маршрутом для поиска задач по идентификатору и предоставляет
 * шаблон с подробной информацией о задаче.
 */
@Controller
public class NewFindTaskController {

    private final JsonDownloader jsonDownloader; // Сервис для загрузки задач

    public NewFindTaskController(JsonDownloader jsonDownloader) {
        this.jsonDownloader = jsonDownloader;
    }

    private List<FindTask> initialTasks = new ArrayList<>(); // Начальные задачи (пустой список для примера)

    /**
     * Обрабатывает GET-запрос для отображения информации о задаче с указанным идентификатором.
     * Если задача с заданным ID найдена, она передается в модель; в противном случае передается null.
     *
     * @param id Идентификатор задачи, передаваемый в URL
     * @param model Модель для передачи данных в представление
     * @return Название шаблона "new-find-task" для отображения данных о задаче
     */
    @GetMapping("/new-find-task/{id}")
    public String findTask(@PathVariable("id") Long id, Model model) {
        // Поиск задачи по ID из начальных данных
        FindTask task = initialTasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseGet(() -> fetchTaskById(id)); // Если задача не найдена, пытаемся получить ее по ID

        model.addAttribute("task", task); // Добавляем задачу в модель (может быть null)
        return "new-find-task"; // Отображаем шаблон "find-task.html"
    }

    /**
     * Метод для получения задачи по ее идентификатору из Jira.
     *
     * @param id Идентификатор задачи
     * @return Объект FindTask или null, если задача не найдена
     */
    private FindTask fetchTaskById(Long id) {
        String issueKey = "KAN-" + id; // Формируем ключ задачи, например, "KAN-3"
        Task task = jsonDownloader.fetchIssueJson(issueKey); // Получаем объект Task из сервиса

        if (task != null) {
            return new FindTask(
                    id,
                    task.getFields().getSummary(),
                    task.getFields().getDescription(),
                    task.getFields().getStatus().getName(),
                    task.getFields().getAssignee() != null ? task.getFields().getAssignee().getDisplayName() : "Не назначен",
                    task.getFields().getCreator() != null ? task.getFields().getCreator().getDisplayName() : "Не указан",
                    task.getFields().getCreated(), // Используйте метод для форматирования даты при необходимости
                    task.getFields().getUpdated()  // Используйте метод для форматирования даты при необходимости
            );
        }
        return null; // Если задача не найдена, возвращаем null
    }
}
