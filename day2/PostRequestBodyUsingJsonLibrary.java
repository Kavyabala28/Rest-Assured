package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class PostRequestBodyUsingJsonLibrary {
	
	//1) Post request body using org.Json Library
	
		@Test(priority = 1)
		void testPostUsingJson() {
			
			JSONObject data = new JSONObject();
			data.put("id", "4");
			data.put("name", "Scott");
			data.put("location", "France");
			data.put("phone", "123456");
			
			String courseArr[] = {"C", "Python"};
			data.put("courses", courseArr);
			
			given()
			      .contentType("application/json")
			      .body(data.toString())
			
			.when()
			      .post("http://localhost:3000/student")
			
			.then()
			      .statusCode(201)
			      .body("name", equalTo("Scott"))
			      .body("location", equalTo("France"))
			      .body("phone", equalTo("123456"))
			      .body("courses[0]", equalTo("C"))
			      .body("courses[1]", equalTo("Python"))
			      .header("Content-Type", "application/json")
			      .log().all();
		}
		

		// delete request
		
		@Test(priority = 2)
		void testDelete() {
			
			given()
			
			.when()
			      .delete("http://localhost:3000/student/4")
			
			.then() 
			      .statusCode(200);
			 		
		}
}
