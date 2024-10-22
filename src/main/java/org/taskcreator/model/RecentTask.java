package org.taskcreator.model;



import java.time.LocalDateTime;

public class RecentTask {
    private Long id;
    private String title;
    private String status;
    private String assignee;
    private LocalDateTime createdAt;

    // Конструкторы, геттеры и сеттеры

    public RecentTask(Long id, String title, String status, String assignee, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.assignee = assignee;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignee() {
        return assignee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
