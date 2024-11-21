package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	    //@Test(priority = 1)
		void testHeaders() {
			
			given()
			
			.when()
			      .get("https://www.google.com/")
			
			.then()
			      .header("Server", "gws")	
			      .header("Content-Encoding", "gzip")
			      .log().all();
		}
	    
	    @Test(priority = 2)
		void getHeaders() {
			
			Response response = given()
			
			.when()
			      .get("https://www.google.com/");
			
			// get single header value
			/*
			 * String headerValue = response.getHeader("Server");
			 * System.out.println(headerValue);
			 */
			
			// Get multiple header values
			Headers allHeadersValue = response.getHeaders();
			for( Header h: allHeadersValue) {
				System.out.println(h.getName() + "-----" +h.getValue());
			}
			      
		}


}
