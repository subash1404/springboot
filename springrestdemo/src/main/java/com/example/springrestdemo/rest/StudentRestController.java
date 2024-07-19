package com.example.springrestdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestdemo.entity.Student;

import jakarta.annotation.PostConstruct;
    
@RestController
@RequestMapping("/test")
public class StudentRestController {
    private List<Student> studList;

    public StudentRestController() {
    }

    // This way student data is loaded only once when the bean is constructed,
    // Not every time when the route is called 
    @PostConstruct
    public void loadData(){
        System.out.println("Student data loaded");
        studList = new ArrayList<>();
        studList.add(new Student("Subash","V"));
        studList.add(new Student("Naveen","Akash"));
    }
    @GetMapping("/students")
    List<Student> getStudents(){
        return studList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if(studentId > studList.size() || studentId < 0)
            throw new StudentNotFoundException("Student Not Found :"+studentId);
        return studList.get(studentId);
    }
}
