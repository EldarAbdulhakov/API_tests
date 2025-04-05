package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.Response.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class BaseRequests {

    public static Integer expectedLastId;
    public static Integer expectedFirstId;

    public static RequestSpecification initRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080/")
                .setContentType(ContentType.JSON)
                .build();
    }

//    public static Integer getCountEntities() {
//        return RestAssured
//                .given()
//                .when()
//                .contentType(ContentType.JSON)
//                .get("http://localhost:8080/api/getAll")
//                .then()
//                .statusCode(200)
//                .extract().body().jsonPath().getList("entity", ResponseEntity.class).size();
//    }

    public static Integer getLastEntityNumberId() {
        List<ResponseEntity> entities = RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/getAll")
                .then()
//                .log().all()
//                .statusCode(200)
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        expectedLastId = entities.get(entities.size() - 1).getId();
        return expectedLastId;
    }

    public static Integer getFirstEntityNumberId() {
        List<ResponseEntity> entities = RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/getAll")
                .then()
//                .log().all()
//                .statusCode(200)
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        expectedFirstId = entities.get(0).getId();
        return expectedFirstId;
    }

    public static List<ResponseEntity> getEntityList() {
        return RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:8080/api/getAll")
                .then()
//                .log().all()
//                .statusCode(200)
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);
    }

    public static String getTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE, dd MMM yyyy HH:mm:ss z",
                Locale.US  // или Locale.ENGLISH
        );
        return "Date: " + now.format(formatter);
    }
}
