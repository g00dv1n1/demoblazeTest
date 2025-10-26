import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddAvatar  {


    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://users.bugred.ru";
    }

    @Test
    void uploadAvatar_multipart() {
        File avatar = new File("src/main/resources/avatar.jpg");

        given()
                .log().all()
                .multiPart("email", "vladislav.shiller@gmail.com")
                .multiPart("avatar", avatar, "image/jpeg")
                .when()
                .post("/tasks/rest/addavatar")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", not(equalTo("error")))
                .extract().response();
    }
}
