package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class CookiesDemo {
	
	/*
	 * Cookies values are dynamic values. So we can't validate the values of cookies
	 */	
	
	//@Test(priority = 1)
	void testCookies() {
		
		given()
		
		.when()
		      .get("https://www.google.com/")
		
		.then()
		      .cookie("AEC", "AVYB7cqibuNUD12HUzYVRN-h3zLhPIZnwBF255Lsg_r8OOGxOHXDrBTfBw")
		      .log().all();
	}
	
	@Test(priority = 2)
	void getCookiesInfo() {
		
		Response response = given()
		
		.when()
		      .get("https://www.google.com/");
		
		// To get value of single cookie
		/*
		 * String cookie_value = response.getCookie("AEC");
		 * System.out.println("Value of AEC Cookies is " +cookie_value);
		 */
		
		// To get all the value of cookies
		Map<String, String>cookies_value = response.getCookies();
		
		//System.out.println(cookies_value.keySet());
		
		for(String k : cookies_value.keySet()) {    // keySet() method will return key values i.e.,("AEC", "NID")
			String cookie_value = response.getCookie(k);
			System.out.println(k + " value = " +cookie_value);
		}
		
		
	}

}
