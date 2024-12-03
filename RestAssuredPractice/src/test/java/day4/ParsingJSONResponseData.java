package day4;

import org.testng.annotations.Test;

import groovy.util.logging.Log;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class ParsingJSONResponseData {

	@Test(priority=1)
	void testJSON() {
		
		// Approach 1
		
		given()
		      .contentType("ContentType.JSON")
		
		.when()
		      .get("http://localhost:3000/store")
		
		.then()
		      .statusCode(200)
		      .header("Content-Type", "application/json")
		      .body("book[3].title", equalTo("The Lord of the Rings"))
		      .log().all();
		}
}
