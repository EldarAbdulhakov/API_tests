package helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Entity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class BaseRequests {

    public static Integer expectedLastId;
    public static Integer expectedFirstId;

    public static String title = "Заголовок сущности";
    public static boolean verified = true;
    public static int page = 1;
    public static int perPage = 10;

    public static RequestSpecification initRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
//        RestAssured.filters(new AllureRestAssured());
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .setBaseUri("http://localhost:8080/")
                .setAccept(ContentType.JSON);
        RequestSpecification requestSpecification;
        return requestSpecification = requestSpecBuilder.build();
    }

    public static void createEntity() {
        Entity entityPojo = Entity.builder().build();
//        Entity entity = new Entity(new Addition("Дополнительные сведения", 123),
//                List.of(42, 87, 15), "Заголовок сущности", true);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(entityPojo)
                .when()
                .post("http://localhost:8080/api/create");
    }

    public static Integer getCountEntities() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class).size();
    }

    public static Integer getLastEntityId() {
        List<Entity> entities = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);

        expectedLastId = entities.get(entities.size() - 1).getId();
        return expectedLastId;
    }

    public static Integer getFirstEntityId() {
        List<Entity> entities = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);

        expectedFirstId = entities.get(0).getId();
        return expectedFirstId;
    }

    public static List<Entity> getEntityList() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);
    }

    public static Optional<Entity> getEntityById(Integer id) {
        List<Entity> entity = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);

        return entity
                .stream()
                .filter(obj -> obj.getId().equals(id))
                .findFirst();
    }

    public static Entity getLastEntity() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/get/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .extract().as(Entity.class);
    }

    public static String getTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE, dd MMM yyyy HH:mm:ss z",
                Locale.US
        );
        return "Date: " + now.format(formatter);
    }
}
