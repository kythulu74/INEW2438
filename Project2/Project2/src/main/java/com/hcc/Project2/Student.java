package com.hcc.Project2;

import org.springframework.stereotype.Component;

@Component
public class Student {
	
	private int id;
    private String first_name;
    private double gpa;
    private String email;
    private String gender;
    
    
    public Student(int id, String first_name, String email, String gender) {
        this.id = id;
        this.first_name = first_name;
        this.email = email;
        this.gender = gender;
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
        return studentString;
    }

}
