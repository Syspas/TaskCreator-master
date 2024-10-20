package org.taskcreator.service;



import org.springframework.stereotype.Service;
import org.taskcreator.model.Task; // Добавьте импорт модели Task

import java.util.List;

@Service
public class TaskService {

    public Task createTask(String title, String description, List<String> fileNames) {
        // Реализуйте логику создания задачи
        // Например, сохранение в базу данных или в другую структуру хранения

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setFileNames(fileNames);

        // Замените эту строку на реальную логику создания задачи
        task.setId(1L); // Предполагаем, что у вас есть способ генерировать id

        return task;
    }
}
