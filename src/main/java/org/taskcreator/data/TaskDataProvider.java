package org.taskcreator.data;

import org.taskcreator.model.RecentTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDataProvider {

    public static List<RecentTask> getAllTasks() {
        List<RecentTask> allTasks = new ArrayList<>();

        allTasks.add(new RecentTask(
                1L,
                "Обращение 1: Проблема с доступом к учетной записи",
                "Выполняется",
                "Иванов Иван",
                LocalDateTime.now().minusDays(1)
        ));

        allTasks.add(new RecentTask(
                2L,
                "Обращение 2: Ошибка при загрузке файла",
                "Завершена",
                "Петров Петр",
                LocalDateTime.now().minusDays(2)
        ));

        allTasks.add(new RecentTask(
                3L,
                "Обращение 3: Пожелание по улучшению интерфейса",
                "В ожидании",
                "Сидоров Сидор",
                LocalDateTime.now().minusDays(3)
        ));

        allTasks.add(new RecentTask(
                4L,
                "Обращение 4: Не удается изменить пароль",
                "Выполняется",
                "Алексеев Алексей",
                LocalDateTime.now().minusDays(5)
        ));

        allTasks.add(new RecentTask(
                5L,
                "Обращение 5: Ошибка в работе приложения",
                "В ожидании",
                "Федоров Федор",
                LocalDateTime.now().minusDays(4)
        ));

        allTasks.add(new RecentTask(
                6L,
                "Обращение 6: Запрос на добавление нового функционала",
                "Завершена",
                "Кузнецов Николай",
                LocalDateTime.now().minusDays(2)
        ));

        allTasks.add(new RecentTask(
                7L,
                "Обращение 7: Вопрос по оплате",
                "Выполняется",
                "Иванова Ирина",
                LocalDateTime.now().minusDays(1)
        ));

        allTasks.add(new RecentTask(
                8L,
                "Обращение 8: Проблема с отображением страницы",
                "Завершена",
                "Семёнов Семён",
                LocalDateTime.now().minusDays(3)
        ));

        allTasks.add(new RecentTask(
                9L,
                "Обращение 9: Запрос на отключение уведомлений",
                "В ожидании",
                "Павлов Павел",
                LocalDateTime.now().minusDays(6)
        ));

        allTasks.add(new RecentTask(
                10L,
                "Обращение 10: Ошибка в расчетах",
                "Выполняется",
                "Сорокин Сергей",
                LocalDateTime.now().minusDays(1)
        ));

        allTasks.add(new RecentTask(
                11L,
                "Обращение 11: Пожелание по улучшению дизайна",
                "Завершена",
                "Лебедев Алексей",
                LocalDateTime.now().minusDays(2)
        ));

        allTasks.add(new RecentTask(
                12L,
                "Обращение 12: Проблема с загрузкой файлов",
                "В ожидании",
                "Григорьев Григорий",
                LocalDateTime.now().minusDays(5)
        ));

        return allTasks;
    }
}
