import org.helpers.BaseRequests;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.pojo.Response;
import org.pojo.User;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateUserTest {

    private String userId;

    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @Test void testCreteUserWithSerialization() {
        User userPojo = User.builder().name("Калугин П.И.").job("Профессор").build();

        userId = given()
                .spec(requestSpecification)
                //.body(new File("src/test/resources/json/userCreationBody.json"))
                .body(userPojo)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(userPojo.getName()), "job", equalTo(userPojo.getJob()))
                .extract().path("id");
    }

    @Test
    public void testCreteUserWithSerializationAndDeserialization() {
        User userPojo = User.builder().job("Ассистент").build();

        Response response = given()
                .spec(requestSpecification)
                .body(userPojo)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract().as(Response.class, ObjectMapperType.GSON);

        userId = response.getId();
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(userPojo.getName(), response.getName());
        softAssertion.assertEquals(userPojo.getJob(), response.getJob());
        softAssertion.assertAll();
    }

    @AfterMethod
    public void deleteUserAfterCreation() { BaseRequests.deleteUserById(userId);}
}
