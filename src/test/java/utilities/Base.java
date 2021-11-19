package utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class Base {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static RequestSpecification userSpec;

    @BeforeAll
    public static void init(){

        baseURI = "URI";//Put URI here
        port = 8080; //Put PORT number here,I just put default port number at this time
        basePath =""; // Put path like id.

        requestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        userSpec =given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("editor", "editor")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);

    }

    @AfterAll
    public static void close(){
        //reset the info we set above, method comes from restAssured
        reset();
    }
}

