package fortna.environment.editor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.NewUtil;
import utilities.Base;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;


@SerenityTest
public class EditorPostTest extends Base {

    @DisplayName("Editor should be able to POST")
    @Test
    public void postAsEditor() {

        Map<String, Object> bodyMap = NewUtil.getRandomMapWithJavaFaker();

        System.out.println("bodyMap = " + bodyMap);

        given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
                .when()
                .post("/something")//put endpoint here
                .then().log().all();


        Ensure.that("Status code is 201", x -> x.statusCode(201));

        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));

        Ensure.that("We also can verify body like some success message",
                thenPart -> thenPart.body("success", is("Created something!")));

        Ensure.that("name is correct",
                thenPart -> thenPart.body("root.name", is(bodyMap.get("name"))));

        Ensure.that("gender is correct",
                thenPart -> thenPart.body("root.gender", is(bodyMap.get("gender"))));

        Ensure.that("phone is correct",
                thenPart -> thenPart.body("root.phone", is(bodyMap.get("phone"))));

    }

    @ParameterizedTest(name = "Something new {index} - name or something: {0}")
    @CsvFileSource(resources = "/FakeData.csv", numLinesToSkip = 1) //My fake data coming from mockaroo.com
    public void postWithCSV(String name, String gender, long phone) {

        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("gender", gender);
        bodyMap.put("phone", phone);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
                .when()
                .post("/endpoint")
                .then().log().all();


        Ensure.that("Status code is 201", x -> x.statusCode(201));

        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));

        Ensure.that("success message here",
                thenPart -> thenPart.body("success", is("Something created")));

        Ensure.that("name is correct",
                thenPart -> thenPart.body("root.name", is(bodyMap.get("name"))));

        Ensure.that("gender is correct",
                thenPart -> thenPart.body("root.gender", is(bodyMap.get("gender"))));

        Ensure.that("phone is correct",
                thenPart -> thenPart.body("root.phone", is(bodyMap.get("phone"))));

    }
}

