import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteAvatar {
    String email = "vladislav.shiller@gmail.com";

    Map<String, Object> body = Map.of("email", email);

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
                .post("/tasks/rest/deleteavatar")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", not(equalTo("error")))
                .extract().response();
    }

}
