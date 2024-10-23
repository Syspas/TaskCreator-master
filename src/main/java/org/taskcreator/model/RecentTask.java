package org.taskcreator.model;

import java.time.LocalDateTime;

/**
 * Класс, представляющий недавнюю задачу.
 */
public class RecentTask {
    private Long id;
    private String title;
    private String description; // Описание задачи
    private String status;
    private String assignee;
    private LocalDateTime createdAt;

    /**
     * Конструктор для инициализации задачи.
     *
     * @param id          идентификатор задачи
     * @param title       заголовок задачи
     * @param description описание задачи
     * @param status      статус задачи
     * @param assignee    исполнитель задачи
     * @param createdAt   дата создания задачи
     */
    public RecentTask(Long id, String title, String description, String status, String assignee, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignee = assignee;
        this.createdAt = createdAt;
    }



    /**
     * Конструктор для инициализации задачи без описания (description).
     *
     * @param id        идентификатор задачи
     * @param title     заголовок задачи
     * @param status    статус задачи
     * @param assignee  исполнитель задачи
     * @param createdAt дата создания задачи
     */
    public RecentTask(Long id, String title, String status, String assignee, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = "тут будет описание проблемы , пока поле для всех заявок одинаковое "; // По умолчанию пустое описание
        this.status = status;
        this.assignee = assignee;
        this.createdAt = createdAt;
    }


    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
