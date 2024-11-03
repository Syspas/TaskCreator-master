package org.taskcreator.model;

import java.time.LocalDateTime;

/**
 * Класс, представляющий задачу с краткой информацией о ее деталях.
 * Содержит данные о заголовке, описании, статусе, исполнителе, создателе задачи и времени создания.
 */
public class RecentTask {

    // Уникальный идентификатор задачи
    private Long id;

    // Краткое название задачи
    private String title;

    // Описание задачи, содержащее дополнительные детали
    private String description;

    // Текущий статус задачи (например, "Выполняется", "Завершена")
    private String status;

    // Имя сотрудника, назначенного на выполнение задачи
    private String assignee;

    // Имя создателя задачи (инициатора запроса или обращения)
    private String creator;

    // Дата и время создания задачи
    private LocalDateTime createdAt;

    /**
     * Полный конструктор для инициализации всех полей задачи.
     *
     * @param id          Уникальный идентификатор задачи
     * @param title       Заголовок задачи
     * @param description Описание задачи, содержащее дополнительные детали
     * @param status      Текущий статус задачи
     * @param assignee    Исполнитель задачи (сотрудник, на которого назначена задача)
     * @param creator     Создатель задачи (инициатор обращения)
     * @param createdAt   Дата и время создания задачи
     */
    public RecentTask(Long id, String title, String description, String status, String assignee, String creator, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignee = assignee;
        this.creator = creator;
        this.createdAt = createdAt;
    }

    /**
     * Конструктор для создания задачи без описания.
     * Используется для случаев, когда описание отсутствует или не требуется.
     * Поле description будет иметь значение "Описание отсутствует" по умолчанию.
     *
     * @param id        Уникальный идентификатор задачи
     * @param title     Заголовок задачи
     * @param status    Текущий статус задачи
     * @param assignee  Исполнитель задачи
     * @param creator   Создатель задачи
     * @param createdAt Дата и время создания задачи
     */
    public RecentTask(Long id, String title, String status, String assignee, String creator, LocalDateTime createdAt) {
        this(id, title, "Описание отсутствует", status, assignee, creator, createdAt);
    }


    // Дополнительный конструктор с 5 параметрами для совместимости с предыдущим кодом
    public RecentTask(Long id, String title, String status, String assignee, LocalDateTime createdAt) {
        this(id, title, "Описание по умолчанию", status, assignee, "Создатель по умолчанию", createdAt);
    }


    // Геттеры и сеттеры для всех полей класса

    /**
     * Получает уникальный идентификатор задачи.
     *
     * @return Идентификатор задачи
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор задачи.
     *
     * @param id Идентификатор задачи
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получает заголовок задачи.
     *
     * @return Заголовок задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок задачи.
     *
     * @param title Заголовок задачи
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получает описание задачи.
     *
     * @return Описание задачи
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание задачи.
     *
     * @param description Описание задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получает текущий статус задачи.
     *
     * @return Статус задачи
     */
    public String getStatus() {
        return status;
    }

    /**
     * Устанавливает статус задачи.
     *
     * @param status Статус задачи
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Получает исполнителя задачи.
     *
     * @return Исполнитель задачи
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * Устанавливает исполнителя задачи.
     *
     * @param assignee Исполнитель задачи
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * Получает имя создателя задачи.
     *
     * @return Имя создателя задачи
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Устанавливает имя создателя задачи.
     *
     * @param creator Имя создателя задачи
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Получает дату и время создания задачи.
     *
     * @return Дата и время создания задачи
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату и время создания задачи.
     *
     * @param createdAt Дата и время создания задачи
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Возвращает строковое представление объекта RecentTask для вывода всех данных о задаче.
     *
     * @return Строка с данными задачи
     */
    @Override
    public String toString() {
        return "RecentTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", assignee='" + assignee + '\'' +
                ", creator='" + creator + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

