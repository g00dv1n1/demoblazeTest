import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class DoRegister {


    String uniq = String.valueOf(System.currentTimeMillis());
    String email = "cu_" + uniq + "@mail.com";
    String name  = "Рест_" + uniq;
    String password = "Pass" + uniq;

    Map<String, Object> bodyRegister = Map.of(
            "email", email,
            "name",  name,
            "password", password
     );

        @BeforeEach
        void setup() {
            RestAssured.baseURI = "http://users.bugred.ru";
        }

        @Test
        void registerUser(){
            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(bodyRegister)
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("type", not(equalTo("error")))
                    .extract().response();
        }
}

