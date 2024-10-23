package org.taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taskcreator.data.TaskDataProvider;
import org.taskcreator.model.RecentTask;

import java.util.List;

/**
 * Контроллер для отображения списка задач.
 * Обрабатывает запросы на просмотр всех задач с поддержкой пагинации.
 */
@Controller
public class TaskListController {

    /**
     * Отображает список задач с пагинацией.
     *
     * @param page номер страницы для отображения
     * @param model модель для передачи данных в шаблон
     * @return имя шаблона для отображения списка задач
     */
    @GetMapping("/view_task")
    public String showTasks(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        List<RecentTask> allTasks = TaskDataProvider.getAllTasks(); // Получение задач из TaskDataProvider

        int pageSize = 5;
        int totalTasks = allTasks.size();
        int totalPages = (int) Math.ceil((double) totalTasks / pageSize);

        // Определяем начало и конец списка задач для текущей страницы
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalTasks);

        // Проверка границ индексов
        if (startIndex > totalTasks || startIndex < 0) {
            startIndex = 0;
        }

        // Получаем задачи для текущей страницы
        List<RecentTask> recentTasks = allTasks.subList(startIndex, endIndex);

        model.addAttribute("recentTasks", recentTasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "view_task"; // Имя Thymeleaf-шаблона
    }
}
