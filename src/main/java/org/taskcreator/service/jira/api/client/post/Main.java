package org.taskcreator.service.jira.api.client.post;


public class Main {
    public static void main(String[] args) {
        // Параметры для создания задачи
        String projectKey = "KAN"; // Замените на ваш ключ проекта
        String summary = "Пример задачи 3"; // Краткое описание задачи
        String description = "Подробное описание задачи 3"; // Полное описание задачи
        String issueTypeId = "10001"; // ID типа задачи, передаваемый из Main

        // Создание экземпляра JiraIssueCreator
        JiraIssueCreator jiraIssueCreator = new JiraIssueCreator();

        // Вызов метода для создания задачи в Jira с переданным issueTypeId
        jiraIssueCreator.createIssue(projectKey, summary, description, issueTypeId);
    }
}

