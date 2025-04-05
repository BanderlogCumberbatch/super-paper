package apitest;

import io.restassured.mapper.ObjectMapperType;
import org.helpers.BaseRequests;
import org.helpers.PropertyProvider;
import org.pojo.Addition;
import org.pojo.Entity;
import org.pojo.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

/**
 * Класс теста получения сущности.
 */
public class GetEntityTest {

    @DataProvider(name = "Add entities data")
    public Object[][] dpMethod() {
        return new Object[][]{
                {PropertyProvider.getInstance().getProperty("prof.entity.title"), true, PropertyProvider.getInstance().getProperty("prof.entity.info")},
        };
    }

    /**
     * Список ID сущностей.
     */
    private final List<String> entitiesId = new ArrayList<>();

    /**
     * Действия перед тестами.
     */
    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    /**
     * Тест получения сущности.
     */
    @Test(description = "Get entity API test", dataProvider = "Add entities data")
    public void testGetEntity(String title, Boolean verified, String additional_info) {
        Entity entityPojo = Entity.builder()
                .title(title).verified(verified)
                .addition(Addition.builder().additional_info(additional_info).build())
                .build();

        entitiesId.add(given()
                .spec(requestSpecification)
                .body(entityPojo)
                .when()
                .post("/api/create")
                .then()
                .statusCode(200)
                .extract().asString());

        Response response = given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/" + entitiesId.get(0))
                .then()
                .statusCode(200)
                .extract().as(Response.class, ObjectMapperType.GSON);

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("prof.entity.title"), response.getTitle(), "Заголовок сущности не совпадает");
        softAssertion.assertEquals(true, response.getVerified().booleanValue(), "Верификация сущности не совпадает");
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("prof.entity.info"), response.getAddition().getAdditional_info(), "Дополнительные сведения сущности не совпадают");
        softAssertion.assertAll();
    }

    /**
     * Действия после тестов.
     */
    @AfterTest
    public void deleteUserAfterCreation() { BaseRequests.deleteEntitiesById(entitiesId);}
}
