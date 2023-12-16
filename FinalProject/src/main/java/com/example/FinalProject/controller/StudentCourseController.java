package com.example.FinalProject.controller;

import com.example.FinalProject.entity.Student;
import com.example.FinalProject.entity.Course;
import com.example.FinalProject.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentCourseController {

	@Autowired
	private StudentCourseService studentCourseService;

	// Save operation
	@PostMapping("/students")
	public Student saveStudent(@Valid @RequestBody Student student) {
		return studentCourseService.saveStudent(student);
	}

	// Read operation
	@GetMapping("/students")
	public List<Student> fetchStudentList() {
		return studentCourseService.fetchStudentList();
	}

	// Update operation
	@PutMapping("/students/{id}")
	public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
		return studentCourseService.updateStudent(student, id);
	}

	// Delete operation
	@DeleteMapping("/students/{id}")
	public String deleteStudentById(@PathVariable("id") Long id) {
		studentCourseService.deleteStudentById(id);

		return "Deleted Successfully";
	}
	
    // Save operation for courses
    @PostMapping("/courses")
    public Course saveCourse(@Valid @RequestBody Course course) {
        return studentCourseService.saveCourse(course);
    }

    // Read operation for courses
    @GetMapping("/courses")
    public List<Course> fetchCourseList() {
        return studentCourseService.fetchCourseList();
    }

    // Update operation for courses
    @PutMapping("/courses/{id}")
    public Course updateCourse(@RequestBody Course course, @PathVariable("id") Long id) {
        return studentCourseService.updateCourse(course, id);
    }

    // Delete operation for courses
    @DeleteMapping("/courses/{id}")
    public String deleteCourseById(@PathVariable("id") Long id) {
        studentCourseService.deleteCourseById(id);
        return "Deleted Successfully";
    }
}
