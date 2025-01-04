package day4;


import java.io.File;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class FileUploadAndDownload {
	
	@Test(priority = 1)
	void singleFileUpload() 
		
	{
		 File myFile = new File("C:\\document\\demo1.txt");
		
		given()
		   .multiPart("file", myFile)
		   .contentType("multipart/form-data")
		   
		.when()
		    .post("http://localhost:8080/uploadFile")
		
		.then()
	        .statusCode(200)
		    .body("fileName", equalTo("demo1.txt"))
		    .log().all();
	}

}
