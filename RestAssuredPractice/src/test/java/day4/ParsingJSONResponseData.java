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
		
		/* Assert.assertEquals(res.statusCode(), 200);   // Validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");*/
		

		// JSONObject
		JSONObject jo = new JSONObject(res.asString());  // Converting response into JSON Object
		
		// Printing all title
		/*for(int i =0; i<jo.getJSONArray("book").length(); i++) {
			String title = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(title);
		}*/
		
		// Search for title of the book in JSON - Validation 1
		boolean status = false;
				
		for(int i =0; i<jo.getJSONArray("book").length(); i++) {
			String title = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			  if(title.equals("The Lord of the Rings")) {
					status = true;
					break;	
			   }
		}
				
		Assert.assertEquals(status, true);
		
		// Validate Total price of books - Validation 2
		
		double totalPrice = 0;
				
		for(int i =0; i<jo.getJSONArray("book").length(); i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
				totalPrice = totalPrice + Double.parseDouble(price);
		}
				
			System.out.println("Total price of all books are : " +totalPrice);
			Assert.assertEquals(totalPrice, 526.0);
	}
}
