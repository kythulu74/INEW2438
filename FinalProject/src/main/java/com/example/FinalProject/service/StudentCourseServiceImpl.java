package com.example.FinalProject.service;

import com.example.FinalProject.entity.Student;
import com.example.FinalProject.entity.Course;
import com.example.FinalProject.repository.StudentRepository;
import com.example.FinalProject.repository.CourseRepository;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class StudentCourseServiceImpl implements StudentCourseService{

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;
	

	// Store student and course data
	@Override
	public void storeStudentCourseDataFromUrl(String url)
			throws IOException, ClientHandlerException, UniformInterfaceException, ParseException {

		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if (clientResponse.getStatus() != 200) {
			throw new RuntimeException("Failed" + clientResponse.toString());
		}
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));
		Iterator<Object> it = jsonArray.iterator();

		while (it.hasNext()) {

			JSONObject jsonObject = (JSONObject) it.next();
			Student student = new Student();
			List<Course> coursesForStudent = new ArrayList<Course>();

			// get and set student first name, email, gender
			student.setFirstName(jsonObject.get("first_name").toString());
			student.setEmail(jsonObject.get("email").toString());
			student.setGender(jsonObject.get("gender").toString());

			// if student has courses get and set student course
			if (jsonObject.containsKey("course")) {
				JSONArray courseArray = (JSONArray) jsonObject.get("course");
				for (Object courseObj : courseArray) {
					Course course = new Course();
					JSONObject courseJson = (JSONObject) courseObj;
					course.setCourseNo(courseJson.get("courseNo").toString());
					course.setGrade(courseJson.get("grade").toString());
					course.setCreditHours(Integer.parseInt(courseJson.get("creditHours").toString()));
					coursesForStudent.add(course);
					// add course to repository
					//saveCourse(course);
				}
				student.setCourses(coursesForStudent);
			}
			// add student to repository
			//saveStudent(student);
		}
	}

	// Save operation
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	// Read operation
	@Override
	public List<Student> fetchStudentList() {
		return (List<Student>) studentRepository.findAll();
	}

	// Update operation
	@Override
	public Student updateStudent(Student student, Long id) {
		Student studentDB = studentRepository.findById(id).get();

		if (Objects.nonNull(student.getFirstName()) && !"".equalsIgnoreCase(student.getFirstName())) {
			studentDB.setFirstName(student.getFirstName());
		}

		return studentRepository.save(studentDB);
	}

	// Delete operation
	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> fetchCourseList() {
		return courseRepository.findAll();
	}

	// Update operation
	@Override
	public Course updateCourse(Course course, Long id) {
		Course courseDB = courseRepository.findById(id).get();

		if (Objects.nonNull(course.getCourseNo()) && !"".equalsIgnoreCase(course.getCourseNo())) {
			courseDB.setCourseNo(course.getCourseNo());
		}

		return courseRepository.save(courseDB);
	}

	@Override
	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}
}