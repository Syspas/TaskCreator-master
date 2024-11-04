package org.taskcreator.service.jira.api.client.post;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

/**
 * Класс для создания задач в Jira.
 */
public class JiraIssueCreator {

    /** URL Jira. */
    private final String jiraUrl;

    /** Имя пользователя Jira. */
    private final String jiraUsername;

    /** Токен API Jira. */
    private final String jiraApiToken;

    /**
     * Создает экземпляр JiraIssueCreator и загружает конфигурацию из файла properties.
     *
     * @throws RuntimeException если файл конфигурации не найден или параметры отсутствуют
     */
    public JiraIssueCreator() {
        // Путь к файлу конфигурации
        String configFilePath = "config.properties";
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new IOException("Файл конфигурации не найден: " + configFilePath);
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке конфигурации: " + e.getMessage());
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }

        // Получение параметров конфигурации
        this.jiraUrl = properties.getProperty("jira.url");
        this.jiraUsername = properties.getProperty("jira.username");
        this.jiraApiToken = properties.getProperty("jira.api.token");

        // Проверка наличия параметров конфигурации
        if (jiraUrl == null || jiraUsername == null || jiraApiToken == null) {
            throw new IllegalArgumentException("Параметры конфигурации не найдены. Проверьте файл конфигурации.");
        }

        // Проверка корректности URL
        if (!jiraUrl.startsWith("http")) {
            throw new IllegalArgumentException("Некорректный URL: " + jiraUrl);
        }

        // Вывод загруженных параметров для отладки
        System.out.println("Загруженный URL: " + this.jiraUrl);
        System.out.println("Загруженное имя пользователя: " + this.jiraUsername);
    }

    /**
     * Создает задачу в Jira с заданными параметрами.
     *
     * @param projectKey ключ проекта, в котором будет создана задача
     * @param summary краткое описание задачи
     * @param description подробное описание задачи
     * @param issueTypeId ID типа задачи
     */
    public void createIssue(String projectKey, String summary, String description, String issueTypeId) {
        try {
            URL url = new URL(jiraUrl + "/rest/api/2/issue");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Установка авторизации
            String auth = jiraUsername + ":" + jiraApiToken;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

            connection.setDoOutput(true);

            // JSON тело запроса
            String jsonInputString = String.format(
                    "{" +
                            "\"fields\": {" +
                            "\"project\": {\"key\": \"%s\"}," +
                            "\"summary\": \"%s\"," +
                            "\"description\": \"%s\"," +
                            "\"issuetype\": {\"id\": \"%s\"}" + // Используем ID типа задачи
                            "}" +
                            "}", projectKey, summary, description, issueTypeId);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Задача успешно создана.");
            } else {
                System.out.println("Ошибка при создании задачи. Код ответа: " + responseCode);

                // Считываем и выводим сообщение об ошибке
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Ответ сервера: " + response.toString());

                    // Проверка кода ответа 400 для вывода дополнительного сообщения
                    if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                        System.out.println("Возможно, указан неверный issueTypeId: " + issueTypeId);
                    }
                }
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
