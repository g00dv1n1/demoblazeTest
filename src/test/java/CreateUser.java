import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class CreateUser {

    String uniq = String.valueOf(System.currentTimeMillis());
    String email = "cu_" + uniq + "@mail.com";
    String name  = "Рест_" + uniq;
    int[] tasks = {12};
    int[] companies = {16, 17};
    String hobby = "Стрельба из лука, Настолки";
    String adres = "адрес 1";
    String name1 = "Тестовый, ясен пень";
    String surname1 = "Иванов";
    String fathername1 = "Петров";
    String cat = "Маруся";
    String dog = "Ушастый";
    String parrot = "Васька";
    String cavy = "Кто ты?";
    String hamster = "Хомяк";
    String squirrel = "Белая горячка к нам пришла";
    String phone = "333 33 33";
    String inn = "123456789012";
    String gender = "m";
    String birthday = "01.01.1900";
    String date_start = "11.11.2000";

    Map<String, Object> body = Map.ofEntries(
            Map.entry("email", email),
            Map.entry("name", name),
            Map.entry("tasks", tasks),
            Map.entry("companies", companies),
            Map.entry("hobby", hobby),
            Map.entry("adres", adres),
            Map.entry("name1", name1),
            Map.entry("surname1", surname1),
            Map.entry("fathername1", fathername1),
            Map.entry("cat", cat),
            Map.entry("dog", dog),
            Map.entry("parrot", parrot),
            Map.entry("cavy", cavy),
            Map.entry("hamster", hamster),
            Map.entry("squirrel", squirrel),
            Map.entry("phone", phone),
            Map.entry("inn", inn),
            Map.entry("gender", gender),
            Map.entry("birthday", birthday),
            Map.entry("date_start", date_start)
    );

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://users.bugred.ru";
    }

    @Test
    void registerUser(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/tasks/rest/createuser")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", not(equalTo("error")))
                .extract().response();
    }
}
