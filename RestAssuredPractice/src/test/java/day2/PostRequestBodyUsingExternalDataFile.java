package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequestBodyUsingExternalDataFile {
	
	//1) Post request body using External data file
	
	@Test(priority = 1)
	void testPostUsingPOJO() throws FileNotFoundException {
		
		File file = new File(".\\body.json");
		
		FileReader fr = new FileReader(file);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
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
