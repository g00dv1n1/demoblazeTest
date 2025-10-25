import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class CreateUserApi {

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://users.bugred.ru";
    }

    @Test
    void createUser(){
        String uniq = String.valueOf(System.currentTimeMillis());
        String email = "cu_" + uniq + "@mail.com";
        String name  = "Рест_" + uniq;

        List<Map<String, Object>> tasks = List.of(
                Map.of("title", "Подготовить отчёт", "description", "Собрать метрики"),
                Map.of("title", "Позвонить клиенту", "description", "Уточнить требования")
        );

        Map<String, Object> body = Map.of(
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
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/tasks/rest/createuserwithtasks")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", not(equalTo("error")))
                .extract().response();
    }
}