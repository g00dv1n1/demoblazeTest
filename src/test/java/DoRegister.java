import PageObject.DoRegisterData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class DoRegister {

        @BeforeEach
        void setup() {
            RestAssured.baseURI = "http://users.bugred.ru";
        }

        @Test
        void registerUser(){
            DoRegisterData data = new DoRegisterData();
            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(data.body)
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("type", not(equalTo("error")))
                    .extract().response();
        }
}

