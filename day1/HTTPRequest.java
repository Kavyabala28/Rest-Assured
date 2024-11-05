package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*given()
      content type, set cookies, add auth, add param, set headers info etc....
when()
      get, post, put, delete
then()
      validate status code, extract response, extract headers cookies & response body...
*/

public class HTTPRequest {

	int id;

	@Test(priority = 1 )
	public void listUser() {

		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 2)
	public void createUser() {

		HashMap hm = new HashMap();
		hm.put("name", "Kavya");
		hm.put("job", "Employee");

		id = given().contentType("application/json").body(hm)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

		/*
		 * .then() .statusCode(201) .log().all();
		 */
	}

	@Test(priority = 3, dependsOnMethods = {"createUser"})
	public void updateUser() {
		
        HashMap hm = new HashMap();
		hm.put("name", "Kavya B");
		hm.put("job", "Tester");

		given()
		      .contentType("application/json")
		      .body(hm)

	    .when()
	          .put("https://reqres.in/api/users/"+id)

	    .then()
	          .statusCode(200)
	          .log().all();
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		
		given()
		
		.when()
		      .delete("https://reqres.in/api/users/"+id)
		.then()
		      .statusCode(204)
		      .log().all();
	}

}
