import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


public class CreateCompany {

    String uniq = String.valueOf(System.currentTimeMillis());
    String email_owner = "vladislav.shiller@gmail.com";
    String company_name  = "Рест_" + uniq;
    enum type {ООО, ИП, ОАО};
    String company_type = type.values()[new java.util.Random().nextInt(type.values().length)].name();
    String [] company_users = {"cu_1761492935447@mail.com", "cu_1761492327947@mail.com"	};

    Map<String, Object> body = Map.of(
            "company_name", company_name,
            "company_type",  company_type,
            "company_users", company_users,
            "email_owner", email_owner
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
                .post("/tasks/rest/createcompany")
                .then()
                .log().all()
                .statusCode(200)
                .body("type", not(equalTo("error")))
                .extract().response();
    }





}
