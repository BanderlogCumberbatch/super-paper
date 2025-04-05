package apitest;

import org.helpers.BaseRequests;
import io.restassured.mapper.ObjectMapperType;
import org.helpers.PropertyProvider;
import org.pojo.Addition;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.pojo.Response;
import org.pojo.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

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
    @Test(description = "Create entity API test", dataProvider = "Add entities data")
    public void testCreteEntityWithSerialization(String title, Boolean verified, String additional_info) {
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

    @Test(description = "Get entity API test")
    public void testGetEntity() {
        Response response = given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/" + entitiesId.get(0))
                .then()
                .statusCode(200)
                .extract().as(Response.class, ObjectMapperType.GSON);

        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("prof.entity.title"), response.getTitle());
        softAssertion.assertEquals(true, response.getVerified().booleanValue());
        softAssertion.assertEquals(PropertyProvider.getInstance().getProperty("prof.entity.info"), response.getAddition().getAdditional_info());
        softAssertion.assertAll();
    }

//    @Test
//    public void del() {
//        BaseRequests.deleteEntitiesById(List.of("21"));
//    }

    /**
     * Действия после тестов.
     */
    @AfterTest
    public void deleteUserAfterCreation() { BaseRequests.deleteEntitiesById(entitiesId);}
}
