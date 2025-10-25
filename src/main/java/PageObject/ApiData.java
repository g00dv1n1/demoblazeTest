package PageObject;

import java.util.List;
import java.util.Map;

public class ApiData {
    String uniq = String.valueOf(System.currentTimeMillis());
    String email = "cu_" + uniq + "@mail.com";
    String name  = "Рест_" + uniq;

    List<Map<String, Object>> tasks = List.of(
            Map.of("title", "Подготовить отчёт", "description", "Собрать метрики"),
            Map.of("title", "Позвонить клиенту", "description", "Уточнить требования")
    );

    public Map<String, Object> body = Map.of(
            "email", email,
            "name",  name,
            "tasks", tasks,
            "hobby", "Стрельба из лука, Настолки",
            "adres", "адрес 1",
            "inn",   "123456789012",         // 12 цифр
            "gender","m",
            "birthday","01.01.1900",
            "date_start","11.11.2000"
    );

}
