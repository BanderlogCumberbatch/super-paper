import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserTest {

    @Test
    public void testGetCorrectFirstName() {

        given()
                .when()
                //.log().all()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }
}
