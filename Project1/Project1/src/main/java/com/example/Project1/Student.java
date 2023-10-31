package com.example.Project1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Scope(value="prototype")
public class Student {

	private int id;
    private String first_name;
    private double gpa;
    private String email;
    private String gender;
    List<Course> courses;
    
    
    public Student(int id, String first_name, String email, String gender, List<Course> courses) {
        this.id = id;
        this.first_name = first_name;
        this.email = email;
        this.gender = gender;
        this.courses = courses;
    }
    
    public Student() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Override
    public String toString() {
        String studentString = "Student \nFirst Name: " + first_name + "\n"+
        		"ID: "+ id +"\n"+
        		"Email: "+ email +"\n"+
        		"Gender: "+ gender;
        if (courses!=null) {
        	studentString+="\nCourses: ";
        	for(Course c: courses ){
        		studentString += c.toString();
        	}	
        }
        return studentString;
    }
    
    public void printCourses() {
    	if (courses != null) {
    		for (Course c: courses) {
    			System.out.println(c.toString());
    		}
    	}
    	else {
    		System.out.println("No courses found");
    	}
    }
    
    public double getGPA() {
    	double gpa = 0;
    	int credits = 0;
    	int credit = 0;
    	double honorPoints =0;
    	if(courses!=null) {
    		for(Course c: courses) {
    			credits+=c.getCreditHours();
    			credit = c.getCreditHours();
    			if(c.getGrade().equals("A")) {
    				gpa=4;
    			}
    			else if(c.getGrade().equals("B")) {
    				gpa=3;
    			}
    			else if(c.getGrade().equals("C")) {
    				gpa=2;
    			}
    			else if(c.getGrade().equals("D")) {
    				gpa=1;
    			}
    			else {
    				gpa=0;
    			}
    			honorPoints+=gpa*credit;
    		}
    		return honorPoints/credits;
    	}
    	else {
    		return gpa;
    	}
    	
    	
    }

	public void setCourses(List<Course> courses) {
		this.courses = courses;		
	}
}
