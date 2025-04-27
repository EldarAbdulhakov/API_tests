package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import models.Entity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateEntityTest {

    private RequestSpecification requestSpecification;

//    @BeforeSuite
//    public static void setup() {
//        RestAssured.filters(new AllureRestAssured());
//    }

    @BeforeClass
    public void setup() {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @AfterMethod
    public void deleteEntity() {
        BaseRequests.deleteEntity();
    }


    @Test
    @Description("Checking the create entity")
    public void testCreateEntity() {
        Entity entity = Entity.builder().build();

//        Entity entity = new Entity(new Addition("Дополнительные сведения", 123),
//                List.of(42, 87, 15), "Заголовок сущности", true);

        String entityId = RestAssured
                .given()
                .spec(requestSpecification)
                .body(entity)
                .when()
                .post("api/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Entity lastEntity = BaseRequests.getEntityById(Integer.parseInt(entityId)).get();

        Assert.assertEquals(Integer.parseInt(entityId), lastEntity.getId());
        Assert.assertEquals(lastEntity.getTitle(), entity.getTitle());
        Assert.assertEquals(lastEntity.getImportant_numbers(), entity.getImportant_numbers());
        Assert.assertEquals(lastEntity.getVerified(), entity.getVerified());
    }
}
