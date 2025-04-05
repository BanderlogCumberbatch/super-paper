package apitest;

import org.helpers.BaseRequests;
import io.restassured.mapper.ObjectMapperType;
import org.helpers.PropertyProvider;
import org.pojo.Addition;
import org.pojo.EntityListResponse;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.pojo.Response;
import org.pojo.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.AssertJUnit.assertFalse;

/**
 * Класс тестов для API сервиса.
 */
public class APIServiceTests {

    @DataProvider(name = "Add entities data")
    public Object[][] dpMethod() {
        return new Object[][]{
                {PropertyProvider.getInstance().getProperty("prof.entity.title"), true, PropertyProvider.getInstance().getProperty("prof.entity.info")},
                {PropertyProvider.getInstance().getProperty("assist.entity.title"), true, PropertyProvider.getInstance().getProperty("assist.entity.info")},
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
     * Тест создания сущности.
     */
    @Test(description = "Create entity API test", dataProvider = "Add entities data", priority = 1)
    public void testCreateEntityWithSerialization(String title, Boolean verified, String additional_info) {
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
    }

    /**
     * Тест получения сущности.
     */
    @Test(description = "Get entity API test", priority = 2)
    public void testGetEntity() {
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
     * Тест обновления сущности.
     */
    @Test(description = "Patch entity API test", priority = 3)
    public void testPatchEntity() {
        Entity entityPojo = Entity.builder()
                .title(PropertyProvider.getInstance().getProperty("prof.unverified.title")).verified(false)
                .addition(Addition.builder().additional_info(PropertyProvider.getInstance().getProperty("prof.unverified.info")).build())
                .build();

        given()
                .spec(requestSpecification)
                .body(entityPojo)
                .when()
                .patch("/api/patch/" + entitiesId.get(0))
                .then()
                .statusCode(204)
                .extract().asString();
    }

    /**
     * Тест получения списка сущностей.
     */
    @Test(description = "Get entities API test", priority = 4)
    public void testGetEntities() {
        EntityListResponse responseWrapper = given()
                .spec(requestSpecification)
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract().as(EntityListResponse.class, ObjectMapperType.GSON);

        List<Response> entities = responseWrapper.getEntity();

        assertFalse("Список сущностей не должен быть пустым", entities.isEmpty());

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("prof.unverified.title"), entities.get(0).getTitle(), "Заголовок первой сущности не совпадает");
        softAssertion.assertEquals(false, entities.get(0).getVerified().booleanValue(), "Верификация первой сущности не совпадает");
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("prof.unverified.info"), entities.get(0).getAddition().getAdditional_info(), "Дополнительные сведения первой сущности не совпадают");
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("assist.entity.title"), entities.get(1).getTitle(), "Заголовок второй сущности не совпадает");
        softAssertion.assertEquals(true, entities.get(1).getVerified().booleanValue(), "Верификация второй сущности не совпадает");
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("assist.entity.info"), entities.get(1).getAddition().getAdditional_info(), "Дополнительные сведения второй сущности не совпадают");
        softAssertion.assertAll();
    }

//    @Test
//    public void del() {
//        BaseRequests.deleteEntitiesById(List.of("53", "59", "63", "70", "76", "81"));
//    }

    /**
     * Действия после тестов.
     */
    @AfterTest
    public void deleteUserAfterCreation() { BaseRequests.deleteEntitiesById(entitiesId);}
}
