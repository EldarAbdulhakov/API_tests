package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojo.Request.Addition;
import pojo.Request.Entity;

import java.util.List;

public class UpdateEntityTest {

    @Test
    @Description("Checking the update entity")
    public void testUpdateEntity() {
        Entity entity = new Entity(new Addition("Дополнительные сведения изменились", 100500),
                List.of(900, 800, 700), "Заголовок сущности, который изменился", false);

        RestAssured
                .given()
                .body(entity)
                .when()
                .contentType(ContentType.JSON)
                .patch("http://localhost:8080/api/patch/%s".formatted(BaseRequests.getLastEntityNumberId()))
                .then()
                .log().all()
                .statusCode(204);


        //headers:  Date: Fri,04 Apr 2025 17:06:51 GMT
    }
}
