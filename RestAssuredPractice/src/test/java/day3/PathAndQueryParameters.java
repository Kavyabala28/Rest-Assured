package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {
	
	// https://reqres.in/api/users?page=2&id=5

	@Test
	void testPathAndQueryParams() {
		
		given()
              .pathParam("myPath", "users")	 // Path Parameter
              .queryParam("page", 2)  // Query Parameter
		      .queryParam("id", 5)    // Query Parameter
		
		.when()
		      .get("https://reqres.in/api/{myPath}")
		
		.then()
		      .statusCode(200)
		      .log().all();
	}
}
