package org.taskcreator.model;

import java.util.List;

public class Task {
    private Long id;
    private String title;
    private String description;
    private List<String> fileNames;


    // Конструктор с аргументами
    public Task(Long id, String title, String description, List<String> fileNames) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileNames = fileNames;
    }

    // Конструктор по умолчанию (необязательный)
    public Task() {
        // Инициализация полей, если необходимо
    }

    // ... геттеры и сеттеры



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

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }









}

