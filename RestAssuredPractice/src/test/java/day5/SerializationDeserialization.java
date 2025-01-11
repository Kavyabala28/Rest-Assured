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
}
