package org.taskcreator.service;

import org.springframework.stereotype.Service;
import org.taskcreator.data.TaskDataProvider;
import org.taskcreator.model.RecentTask;
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

    public void loadTestData() {
        List<RecentTask> allTasks = TaskDataProvider.getAllTasks(); // Получение задач из TaskDataProvider

        // Перебираем все задачи и добавляем их в tasks
        for (RecentTask recentTask : allTasks) {
            Task task = new Task(
                    recentTask.getId(), // Используем идентификатор RecentTask
                    recentTask.getTitle(), // Заголовок задачи
                    recentTask.getDescription(), // Описание задачи, если оно есть, либо можно оставить пустым
                    List.of() // Добавляем пустой список файлов, если это не предусмотрено
            );
            tasks.add(task);
        }
    }

    // Метод для получения всех задач
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Возвращает копию списка задач
    }

    // Метод для получения недавних задач
    public List<RecentTask> getRecentTasks() {
        return TaskDataProvider.getAllTasks(); // Получение недавних задач из TaskDataProvider
    }
}
