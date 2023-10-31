package com.example.Project1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Project1Application {
	
	static List<Student> studentList = new ArrayList<Student>();
	static List<Course> courses = new ArrayList<Course>();

	public static void main(String[] args) throws ClientHandlerException, UniformInterfaceException, ParseException {
		ConfigurableApplicationContext context = SpringApplication.run(Project1Application.class, args);
		
		String url = "https://hccs-advancejava.s3.amazonaws.com/student_course.json";
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if (clientResponse.getStatus()!=200) {
			throw new RuntimeException("Failed"+clientResponse.toString());
		}
		
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));
		Iterator<Object> it = jsonArray.iterator();
		
		while (it.hasNext()) {
			
			JSONObject jsonObject = (JSONObject)it.next();
			Student student = context.getBean(Student.class);
			List<Course> coursesForStudent = new ArrayList<Course>();
			
			
			student.setId(Integer.parseInt(jsonObject.get("id").toString()));
			student.setFirst_name(jsonObject.get("first_name").toString());
			student.setEmail(jsonObject.get("email").toString());
			student.setGender(jsonObject.get("gender").toString());
			
			if (jsonObject.containsKey("course")) {
	            JSONArray courseArray = (JSONArray) jsonObject.get("course");
	            for (Object courseObj : courseArray) {
	            	Course course = context.getBean(Course.class);
	                JSONObject courseJson = (JSONObject) courseObj;
	                course.setCourseNo(courseJson.get("courseNo").toString());
	                course.setGrade(courseJson.get("grade").toString());
	                course.setCreditHours(Integer.parseInt(courseJson.get("creditHours").toString()));
	                coursesForStudent.add(course);
	                courses.add(course);
	                
	            }
	            student.setCourses(coursesForStudent);
	        }
			studentList.add(student);			
		}
		
		findStudent("Alex");
		findCourse("CS");
		getAllGrades();
		
		
	}
	
	private static void findCourse(String s) {
		boolean found = false;
		for (Course c: courses) {
			if(c.getCourseNo().equals(s)) {
				System.out.println("Course Found: ");
				System.out.println(c.toString());
				found = true;
			}
		}
		if(!found) {
			System.out.println("Course not found");
		}
	}

	public static void findStudent(String n) {
		boolean found = false;
		for (Student s: studentList) {
			if(s.getFirst_name().equals(n)) {
				System.out.println("Student Found: ");
				System.out.println(s.toString());
				found = true;
			}
		}
		if(!found) {
			System.out.println("Student not found");
		}
	}
	
	public static void getAllGrades() {
		for(int i=0;i<studentList.size();i++) {
			System.out.println(studentList.get(i).getFirst_name()+"'s GPA: "+studentList.get(i).getGPA());
		}
	}

}


