package org.taskcreator.service;

import org.springframework.stereotype.Service;
import org.taskcreator.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    // Используйте базу данных или другой механизм хранения в реальном проекте
    private List<Task> tasks = new ArrayList<>();

    public Task createTask(String title, String description, List<String> fileNames) {
        // Генерация ID (в реальном проекте используйте базу данных)
        long nextId = tasks.isEmpty() ? 1L : tasks.get(tasks.size() - 1).getId() + 1;

        Task task = new Task(nextId, title, description, fileNames);
        tasks.add(task);
        return task;
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> optionalTask = tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();

        return optionalTask.orElse(null); // Возврат null, если задача не найдена
    }

    // Метод для добавления тестовых данных (необязателен в реальном проекте)
    public void loadTestData() {
        Task task1 = new Task(1L, "Создать отчет", "Подготовить отчет о продажах за последний квартал",
                List.of("report.pdf", "data.xlsx"));
        Task task2 = new Task(2L, "Исправить ошибку", "Исправить ошибку в коде на странице входа",
                List.of("error.log"));

        tasks.add(task1);
        tasks.add(task2);
    }



    // Метод для получения всех задач
    public List<Task> getAllTasks() {
        //return new ArrayList<>(tasks);
        return tasks;
    }
}
