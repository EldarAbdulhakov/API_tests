package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteEntityTest {

    @Test
    @Description("Checking the delete first entity")
    public void testDeleteEntity() {
        RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .delete("http://localhost:8080/api/delete/%s".formatted(BaseRequests.getFirstEntityNumberId()))
                .then()
                .log().all()
                .statusCode(204);

        Assert.assertFalse(BaseRequests.getEntityList()
                .stream()
                .anyMatch(obj -> obj.getId().equals(BaseRequests.expectedFirstId)));

        //проверить, что действительно в списке нет удаленной сущности
        //сравнить ответ в хедере: date: Fri,04 Apr 2025 14:56:29 GMT
    }
}
