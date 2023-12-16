package com.example.FinalProject.service;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import com.example.FinalProject.entity.Student;
import com.example.FinalProject.entity.Course;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;


public interface StudentCourseService {
	
	//Store student and course data
	void storeStudentCourseDataFromUrl(String url) throws IOException, ClientHandlerException, UniformInterfaceException, ParseException;

	// Save operation
	Student saveStudent(Student student);

	// Read operation
	List<Student> fetchStudentList();

	// Update operation
	Student updateStudent(Student student, Long id);

	// Delete operation
	void deleteStudentById(Long id);
	
	// Save operation
	Course saveCourse(Course course);

	// Read operation
	List<Course> fetchCourseList();

	// Update operation
	Course updateCourse(Course course, Long id);

	// Delete operation
	void deleteCourseById(Long id);
}
