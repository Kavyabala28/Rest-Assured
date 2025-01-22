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
	   

	   // 3. Preemptive
	   @Test(priority = 3)
       void testPreemptiveAuthentication() {
		
		given()
		     .auth().preemptive().basic("postman", "password")
		
		.when()
		     .get("https://postman-echo.com/basic-auth")
		
		.then()
		      .statusCode(200)
		      .body("authenticated", equalTo(true))
		      .log().all();
	}
	   
	   // 4. Bearer Token
	   @Test(priority = 4)
	   void testBearerTokenAuthentication() {
		   
		   String token = "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";
		   
		   given()
		         .header("Authorization", "Bearer "+token)
		   
		   .when()
		         .get("https://api.github.com/user/repos")
		   
		   .then()
		         .statusCode(200)
		         .log().all();
		   
	   }
	   
	   
	   // 5. OAuth1.0
	   @Test
	   void testOAuth1Authentication() {
		   
		   given()
		        .auth().oauth("consumerkey", "consumerSecrete", "accessToken", "tokenSecrete") // This is OAuth1.0 Authentication
		   
		   .when()
		        .get("url")
		   
		   .then()
		        .statusCode(200);
	   }
	   
	// 6. OAuth2.0
	   @Test
	   void testOAuth2Authentication() {
		   
		   given()
		        .auth().oauth2("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") // This is OAuth1.0 Authentication
		   
		   .when()
		        .get("https://api.github.com/user/repos")
		   
		   .then()
		        .statusCode(200)
		        .log().all();
	   }
	   
	// 7. API Key
	   @Test
	   void testAPIKeyAuthentication() {
		   
		   // Method 1
		   given()
		        .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")   // appid is APIkey
		   
		   .when()
		        .get("https://api.oepnweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		   
		   .then()
		       .statusCode(200)
		       .log().all();
	   }
}
