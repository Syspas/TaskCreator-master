package org.taskcreator.service.jira.api.client.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.taskcreator.service.jira.api.client.get.date.Task;


import java.io.IOException;

/**
 * Класс для парсинга JSON-данных в объект Task.
 */
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    public Task parseJsonFromUrl(String json) throws IOException {
        return objectMapper.readValue(json, Task.class);
    }
}
