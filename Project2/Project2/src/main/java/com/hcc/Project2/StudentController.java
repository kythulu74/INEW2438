package com.hcc.Project2;

import java.io.*;
import java.util.*;

import org.springframework.boot.context.properties.bind.BindContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	public static List<Student> readData() throws IOException{
		
		String url = "C:/Users/kylef/OneDrive/Desktop/students.txt";
		FileReader fileReader = new FileReader(url);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Student> studentList = new ArrayList();
        
        String header = bufferedReader.readLine();
        String line = bufferedReader.readLine();
        
        while(line != null) {
        	Student student = new Student();
        	String[] data = line.split(",");
        	student.setId(Integer.parseInt(data[0]));
        	student.setFirst_name(data[1]);
        	student.setGpa(Double.parseDouble(data[2]));
        	student.setEmail(data[3]);
        	student.setGender(data[4]);
        	studentList.add(student);
        	line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return studentList;        
	}
	
	@GetMapping("/students")
    public List<Student>  students() throws IOException {
        return readData();
    }

	@GetMapping("/name/{first_name}")
    public Student student(@PathVariable String first_name) throws IOException {
        System.out.println("search student by first name : "+first_name);
        List<Student> students = readData();
        for(Student student : students){
            if( student.getFirst_name().equalsIgnoreCase(first_name)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found with name "+first_name);
        return null;
    }
	
	@GetMapping("/student")
    public Student student(@RequestParam double gpa, @RequestParam String gender) throws IOException {
        System.out.println("search student by gpa : "+gpa+" gender : "+gender);
        List<Student> students = readData();
        for(Student student : students){
            if( student.getGpa()==gpa && student.getGender().equalsIgnoreCase(gender)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found for gpa "+gpa + " and gender "+gender);
        return null;
    }
	
	@GetMapping("/gpa")
	public double averageGPA() throws IOException {
		List<Student> students = readData();
		double gpaSum = 0;
		int count =0;
		for(Student student: students) {
			gpaSum+=student.getGpa();
			count++;
		}
		return gpaSum/count;
	}
}
