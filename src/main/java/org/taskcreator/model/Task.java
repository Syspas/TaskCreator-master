package org.taskcreator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет задачу с уникальным идентификатором, заголовком, описанием и связанными именами файлов.
 */
public class Task {
    private Long id;
    private String title;
    private String description;
    private List<String> fileNames;

    /**
     * Конструирует новую задачу с указанными параметрами.
     *
     * @param id          уникальный идентификатор задачи
     * @param title       заголовок задачи
     * @param description описание задачи
     * @param fileNames   список имен файлов, связанных с задачей
     */
    public Task(Long id, String title, String description, List<String> fileNames) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileNames = fileNames != null ? new ArrayList<>(fileNames) : new ArrayList<>();
    }

    /**
     * Конструирует новую задачу с умолчательными значениями.
     * Инициализирует fileNames как пустой список.
     */
    public Task() {
        this.fileNames = new ArrayList<>();
    }

    /**
     * Возвращает уникальный идентификатор задачи.
     *
     * @return идентификатор задачи
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор задачи.
     *
     * @param id новый идентификатор задачи
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает заголовок задачи.
     *
     * @return заголовок задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок задачи.
     *
     * @param title новый заголовок задачи
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание задачи.
     *
     * @return описание задачи
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание задачи.
     *
     * @param description новое описание задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает список имен файлов, связанных с задачей.
     *
     * @return копия списка имен файлов
     */
    public List<String> getFileNames() {
        return new ArrayList<>(fileNames);
    }

    /**
     * Устанавливает список имен файлов, связанных с задачей.
     *
     * @param fileNames новый список имен файлов
     */
    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames != null ? new ArrayList<>(fileNames) : new ArrayList<>();
    }
}
