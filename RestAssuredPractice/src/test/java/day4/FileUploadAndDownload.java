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
	
	@Test
		void multipleFilesUpload() 
			
		{
			 File myFile1 = new File("C:\\document\\demo1.txt");
			 File myFile2 = new File("C:\\document\\demo2.txt");
			
			given()
			   .multiPart("files", myFile1)
			   .multiPart("files", myFile2)
			   .contentType("multipart/form-data")
			   
			.when()
			    .post("http://localhost:8080/uploadMultipleFiles")
			
			.then()
		        .statusCode(200)
			    .body("[0].fileName", equalTo("demo1.txt"))
			    .body("[1].fileName", equalTo("demo2.txt"))
			    .log().all();
		}

}
