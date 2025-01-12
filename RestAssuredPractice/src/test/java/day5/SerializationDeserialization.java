package day5;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.POJORequestBody;

// POJO ------Serialize------> JSONObject -------DeSerialize-------> POJO

public class SerializationDeserialization {

	// Serialization - POJO ---------> JSON Object
	//@Test
	void convertPojoToJson() throws JsonProcessingException {

		// Created Java object using POJO class
		Student stuPojo = new Student();
		stuPojo.setId("4");
		stuPojo.setName("Scott");
		stuPojo.setLocation("France");
		stuPojo.setPhone("123456");

		String coursesArr[] = { "C", "Python" };
		stuPojo.setCourses(coursesArr);

		// Convert JSON ----> JSON object (Serialization)
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPojo);
		System.out.println(jsonData);

	}
	
	// DeSerialization - JSON Object ---------> Pojo
		@Test
		void convertJsonToPojo() throws JsonProcessingException {

			String jsondata = "{\r\n" + "  \"id\" : \"4\",\r\n" + "  \"name\" : \"Scott\",\r\n"
					+ "  \"location\" : \"France\",\r\n" + "  \"phone\" : \"123456\",\r\n"
					+ "  \"courses\" : [ \"C\", \"Python\" ]\r\n" + "}";

			// Convert Json data ---------> POJO 
			ObjectMapper objMapper =  new ObjectMapper();
			Student stuPojo = objMapper.readValue(jsondata, Student.class);  // Convert JSON to POJO
			
			System.out.println("Id:" +stuPojo.getId());
			System.out.println("Name:" +stuPojo.getName());
			System.out.println("Location:" +stuPojo.getLocation());
			System.out.println("Phone:" +stuPojo.getPhone());
			System.out.println("Course1:" +stuPojo.getCourses()[0]);
			System.out.println("Course2:" +stuPojo.getCourses()[1]);
		}
}
