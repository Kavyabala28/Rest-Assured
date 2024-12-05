package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;


public class ParsingJSONResponseData {

	//@Test(priority=1)
	void testJSON() {
		
		// Approach 1
		
		/*
		 * given() .contentType(ContentType.JSON)
		 * 
		 * .when() .get("http://localhost:3000/store")
		 * 
		 * .then() .statusCode(200) .header("Content-Type", "application/json")
		 * .body("book[3].title", equalTo("The Lord of the Rings")) .log().all();
		 */
		
		// Approach 2
		
		Response res  = given()
		     .contentType(ContentType.JSON)
		
		.when()
		      .get("http://localhost:3000/store");
		
		Assert.assertEquals(res.statusCode(), 200);   // Validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");
		
		}
	
	@Test(priority=2)
	void testJSONResponseBodyData() {
		
		Response res  = given()
		     .contentType(ContentType.JSON)
		
		.when()
		      .get("http://localhost:3000/store");
		
		 Assert.assertEquals(res.statusCode(), 200);   // Validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");
	
	}
}
