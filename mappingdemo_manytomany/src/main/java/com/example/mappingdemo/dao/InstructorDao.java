package com.example.mappingdemo.dao;

import java.util.List;

import com.example.mappingdemo.entity.Course;
import com.example.mappingdemo.entity.Instructor;
import com.example.mappingdemo.entity.InstructorDetail;
import com.example.mappingdemo.entity.Student;

public interface InstructorDao{
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail fInstructorDetailById(int id);
    void deleteInstructorDetailsById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    Instructor updateInstructor(Instructor instructor);
    void deleteInstructor(int id);
    void deleteCourse(int id);
    void saveCourse(Course course);
    Course findCourseAndReviews(int id);
    void deleteCouseWithReviews(int id);
    Course findCourseAndStudentsByCourseId(int id);
    Student findCourseAndStudentsByStudentId(int id);
    void update(Student student);
    void deleteStudent(int id);
}