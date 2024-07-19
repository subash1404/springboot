package com.example.cruddemo.Dao;

import java.util.List;

import com.example.cruddemo.entity.Student;

public interface  StudentDao {
    Student findById(Integer id);
    void save(Student theStudent);
    List<Student> findAll();
    List<Student> findByEmail(String email);
    void updateStudent(Student theStudent);
    void removeStudent(Student theStudent);
    void deleteStudent(Integer id);
}
