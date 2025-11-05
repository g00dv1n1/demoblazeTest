package APItests;

import PointObject.CreateCompanyData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class CreateCompany {

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://users.bugred.ru";
    }

    @Test
    void registerUser(){
        CreateCompanyData data = new CreateCompanyData();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(data.body)
                .when()
                .post("/tasks/rest/createcompany")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", not(equalTo("error")))
                .extract().response();
    }
}
