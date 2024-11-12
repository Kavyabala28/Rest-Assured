package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostRequestBodyUsingPOJOClass {
	
	//1) Post request body using POJO Class
	
			@Test(priority = 1)
			void testPostUsingPOJO() {
				
				POJORequestBody data = new POJORequestBody();
				data.setId("4");
				data.setName("Scott");
				data.setLocation("France");
				data.setPhone("123456");
				 
				String coursesArr[] = {"C", "Python"};
				data.setCourses(coursesArr);
				
				given()
				      .contentType("application/json")
				      .body(data)
				
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
