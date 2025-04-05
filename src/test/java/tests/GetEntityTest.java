package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class GetEntityTest {

//    @BeforeClass
//    public void setup() {
//        BaseRequests.initRequestSpecification();
//    }

    @Test
    @Description("Checking the get last entity")
    public void testGetEntity() {
        RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/get/%s".formatted(BaseRequests.getLastEntityNumberId()))
                .then()
                .log().all()
                .statusCode(200);

        //проверить все поля
    }

    @Test
    @Description("Checking the get all entities")
    public void testGetAllEntities() {
        RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/getAll")
                .then()
                .log().all()
                .statusCode(200);

        //проверить ???????????? посмотреть проверки ниже
    }

//    @Test
//    @Description("Checking the get all entities №2")
//    public void testGetAllEntities2() {
//        List<ResponseEntity> entities = RestAssured
//                .given()
//                .when()
//                .contentType(ContentType.JSON)
//                .get("api/getAll")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract().body().jsonPath().getList("entity", ResponseEntity.class);
//
//        entities.forEach(i -> Assert.assertEquals(i.getTitle(), "Заголовок сущности"));
//        Assert.assertEquals(entities.size(), 47);
//    }
}
