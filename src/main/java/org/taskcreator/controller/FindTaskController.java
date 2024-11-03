package org.taskcreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.taskcreator.model.RecentTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов на отображение информации о задачах.
 * Управляет маршрутом для поиска задач по идентификатору и предоставляет
 * шаблон с подробной информацией о задаче.
 */
@Controller
public class FindTaskController {

    /**
     * Начальные данные: список задач.
     * Эти задачи служат для демонстрации функционала, как если бы они были
     * извлечены из базы данных.
     */
    /**
     * Начальные данные: список задач.
     * Эти задачи служат для демонстрации функционала, как если бы они были
     * извлечены из базы данных.
     */
    private static final List<RecentTask> initialTasks = new ArrayList<>();

    static {
        initialTasks.add(new RecentTask(
                1L,
                "Обращение 1: Проблема с доступом к учетной записи",
                "Проблема с авторизацией: пользователь не может войти в систему, ошибка аутентификации.",
                "Выполняется",
                "Иванов Константин",
                "Алексей Смирнов",
                LocalDateTime.now().minusDays(1)
        ));

        initialTasks.add(new RecentTask(
                2L,
                "Обращение 2: Ошибка при загрузке файла",
                "При загрузке файла формата .docx возникает ошибка формата. Не удается открыть файл.",
                "Завершена",
                "Петров Петр",
                "Мария Козлова",
                LocalDateTime.now().minusDays(2)
        ));

        initialTasks.add(new RecentTask(
                3L,
                "Обращение 3: Пожелание по улучшению интерфейса",
                "Пожелание улучшить интерфейс, добавить темную тему и изменить расположение меню.",
                "В ожидании",
                "Сидоров Сидор",
                "Николай Волков",
                LocalDateTime.now().minusDays(3)
        ));
    }

    /**
     * Обрабатывает GET-запрос для отображения информации о задаче с указанным идентификатором.
     * Если задача с заданным ID найдена, она передается в модель; в противном случае передается null.
     *
     * @param id Идентификатор задачи, передаваемый в URL
     * @param model Модель для передачи данных в представление
     * @return Название шаблона "find-task" для отображения данных о задаче
     */
    @GetMapping("/find-task/{id}")
    public String findTask(@PathVariable("id") Long id, Model model) {
        // Поиск задачи по ID из начальных данных
        RecentTask task = initialTasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null); // Если задача не найдена, возвращаем null

        model.addAttribute("task", task); // Добавляем задачу в модель (может быть null)
        return "find-task"; // Отображаем шаблон "find-task.html"
    }
}
