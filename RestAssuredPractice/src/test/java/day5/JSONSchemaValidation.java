package day5;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

// Json ---> Json Schema Converter - https://jsonformatter.org/json-to-jsonschema


public class JSONSchemaValidation {
	
	// Schema Validation -- Type of Data (String, number etc)
	
	@Test
	void jsonSchemaValidation() {
		
		given()
		
		.when()
		      .get("http://localhost:3000/store")
		
		.then()
		      .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
	}

}
