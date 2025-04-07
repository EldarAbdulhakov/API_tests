package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import models.Addition;
import models.Entity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.requestSpecification;

public class UpdateEntityTest {

    @BeforeClass
    public void setup() {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @Test
    @Description("Checking the update entity")
    public void testUpdateEntity() {
        Entity entity = Entity.builder().
                title("Заголовок сущности, который изменился").
                important_numbers(List.of(900, 800, 700))
                .verified(false)
                .addition(Addition.builder()
                        .additional_info("Дополнительные сведения изменились")
                        .additional_number(100500)
                        .build())
                .build();
//        Entity entity = new Entity(new Addition("Дополнительные сведения изменились", 100500),
//                List.of(900, 800, 700), "Заголовок сущности, который изменился", false);

        RestAssured
                .given()
                .spec(requestSpecification)
                .body(entity)
                .when()
                .patch("api/patch/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .log().all()
                .statusCode(204);

        Assert.assertEquals(BaseRequests.getLastEntity().getId(), BaseRequests.expectedLastId);
        Assert.assertEquals(BaseRequests.getLastEntity().getTitle(), entity.getTitle());
        Assert.assertEquals(BaseRequests.getLastEntity().getVerified(), entity.getVerified());
        Assert.assertEquals(BaseRequests.getLastEntity().getImportant_numbers(), entity.getImportant_numbers());
    }
}
