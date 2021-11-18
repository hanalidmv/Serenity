package fortna.environment.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.baseURI;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;

//@Disabled --> We also can use Disabled annotations if we don't want to get report. It will ignore it.
@SerenityTest
public class AdminGetTest {

    @BeforeAll
    public static void init() {

        baseURI = ""; //We can put any URL inside the double quotes.

    }

    @Test
    public void getAll() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("")//we can insert our end point here
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    public void getOne() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .pathParam("", 1)// we can put any path param such as id or etc. then put number.
                .when()
                .get(""); //put end point here.

       //we can use lastResponse() without being saved separately. Also it is same with Response response object.

        System.out.println("Status code = " + lastResponse().statusCode());

        System.out.println("lastResponse().path(\"\") = " + lastResponse().path(""));

        String something = lastResponse().jsonPath().getString(""); //we can put name, gender and etc.
        System.out.println("something = " + something);
    }


    @DisplayName("GET request with Serenity Assertion way")
    @Test
    public void getOneAssertion() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .pathParam("", 1)//we can put id or any path
                .when()
                .get("");//put end point here

        //Serenity way of assertion

        Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200));

        Ensure.that("Content-type is JSON", vRes -> vRes.contentType(ContentType.JSON));

        Ensure.that("Something is 1", vRes -> vRes.body("", is(1)));

    }
}

