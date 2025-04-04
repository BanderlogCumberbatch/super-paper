package org.helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

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
     * @param userId id пользователя, которого необходимо удалить
     */
    public static void deleteUserById(String userId) {

        given()
                .when()
                .delete("api/users" + userId)
                .then()
                .statusCode(204);
    }
}
