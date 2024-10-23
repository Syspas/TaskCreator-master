package org.taskcreator.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.taskcreator.model.RecentTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @GetMapping("/")
    public String redirectToHomeOrLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, аутентифицирован ли пользователь
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home"; // Перенаправление на домашнюю страницу
        } else {
            return "redirect:/login"; // Перенаправление на страницу входа
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // имя представления для страницы логина
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Создаем список последних задач
        List<RecentTask> recentTasks = new ArrayList<>();
        recentTasks.add(new RecentTask(1L, "Обращение 1: Проблема с доступом к учетной записи", "Выполняется", "Иванов Иван", LocalDateTime.now().minusDays(1)));
        recentTasks.add(new RecentTask(2L, "Обращение 2: Ошибка при загрузке файла", "Завершена", "Петров Петр", LocalDateTime.now().minusDays(2)));
        recentTasks.add(new RecentTask(3L, "Обращение 3: Пожелание по улучшению интерфейса", "В ожидании", "Сидоров Сидор", LocalDateTime.now().minusDays(3)));

        model.addAttribute("recentTasks", recentTasks);  // Добавляем список в модель
        return "home"; // Название вашего шаблона
    }

    @GetMapping("/create_task")
    public String createTask() {
        return "create_task"; // имя представления для страницы создания обращения
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings"; // имя представления для страницы настроек
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success"; // имя представления для страницы завершения сеанса
    }

    @GetMapping("/logout")
    public String logout() {
        // Можно добавить логику, если необходимо
        return "logout-success"; // имя представления для страницы завершения сеанса
    }
}
