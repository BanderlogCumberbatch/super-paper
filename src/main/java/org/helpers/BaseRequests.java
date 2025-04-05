package org.helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

/**
 * Базовый класс спецификации запроса.
 */
public class BaseRequests {

    /**
     * Подготовка спецификации запроса
     * @return спецификация
     */
    public static RequestSpecification initRequestSpecification() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyProvider.getInstance().getProperty("api.url"))
                .setAccept(ContentType.JSON);
        return requestSpecification = requestSpecBuilder.build();
    }

    /**
     * Подготовка спецификации запроса
     *
     * @param entitiesId список id пользователей, которых необходимо удалить
     */
    public static void deleteEntitiesById(List<String> entitiesId) {

        for (String entityId : entitiesId) {
            given()
                    .when()
                    .delete("/api/delete/" + entityId)
                    .then()
                    .statusCode(204);
        }
    }
}
