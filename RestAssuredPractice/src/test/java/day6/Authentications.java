package day6;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	
	// Types of Authentications
	
	// 1. Basic 
	@Test(priority = 1)
	void testBasicAuthentication() {
		
		given()
		     .auth().basic("postman", "password")
		
		.when()
		     .get("https://postman-echo.com/basic-auth")
		
		.then()
		      .statusCode(200)
		      .body("authenticated", equalTo(true))
		      .log().all();
	}
	

	// 2. Digest
	   @Test(priority = 2)
       void testDigestAuthentication() {
		
		given()
		     .auth().digest("postman", "password")
		
		.when()
		     .get("https://postman-echo.com/basic-auth")
		
		.then()
		      .statusCode(200)
		      .body("authenticated", equalTo(true))
		      .log().all();
	}

}
