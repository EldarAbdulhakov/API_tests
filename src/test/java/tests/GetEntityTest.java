package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import models.Entity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.requestSpecification;

public class GetEntityTest {

    @BeforeClass
    public void setup() {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

//    @BeforeClass
//    public void setup() {
//        BaseRequests.initRequestSpecification();
//    }

    @Test
    @Description("Checking the get last entity")
    public void testGetEntity() {
        Entity entity = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("api/get/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .statusCode(200)
                .extract().as(Entity.class);

        Assert.assertEquals(entity.getId(), BaseRequests.getLastEntityId());
    }

    @Test
    @Description("Checking the get all entities")
    public void testGetAllEntities() {
        List<Entity> entityList = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("api/getAll")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("entity", Entity.class);

        Assert.assertEquals(entityList.size(), BaseRequests.getCountEntities());
    }

    @Test
    @Description("Checking the get all entities with all parameters")
    public void testGetAllEntitiesParameterized() {
        List<Entity> entityList = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("api/getAll%s".formatted("?title=" + BaseRequests.title + "&verified="
                        + BaseRequests.verified + "&page=" + BaseRequests.page + "&perPage=" + BaseRequests.perPage))
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("entity", Entity.class);

        entityList.forEach(obj -> Assert.assertEquals(obj.getTitle(), BaseRequests.title));
        entityList.forEach(obj -> Assert.assertEquals(obj.getVerified(), BaseRequests.verified));
    }
}
