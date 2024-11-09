package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

/*Ways to create Post request:
1) Post request body using HashMap
2) Post request body using org.json library
3) Post request body using POJO (Plain Old Java Object)
4) Post request body using external JSON file data
*/

public class PostRequestBodyUsingHashMap {

	//1) Post request body using HashMap
	
	//@Test(priority = 1)
	void testPostUsingHashMap() {
		
		HashMap data = new HashMap();
		data.put("name", "Divya");
		data.put("location", "Chennai");
		data.put("phone", "1234");
		
		String courseArr[] = {"C","C++"};
		data.put("courses", courseArr);
		
		given()
		      .contentType("application/json")
		      .body(data)
		
		.when()
		      .post("http://localhost:3000/student")
		
		.then()
		      .statusCode(201)
		      .body("name", equalTo("Divya"))
		      .body("location", equalTo("Chennai"))
		      .body("phone", equalTo("1234"))
		      .body("courses[0]", equalTo("C"))
		      .body("courses[1]", equalTo("C++"))
		      .header("Content-Type", "application/json")
		      .log().all();
	}
	
	// delete request
	
	@Test(priority = 2)
	void testDelete() {
		
		given()
		
		.when()
		      .delete("http://localhost:3000/student/6619");
		
		//.then()
		    //  .statusCode(204);
	}
}
