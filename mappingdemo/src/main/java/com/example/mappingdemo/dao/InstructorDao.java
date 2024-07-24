package com.example.mappingdemo.dao;

import com.example.mappingdemo.entity.Instructor;
import com.example.mappingdemo.entity.InstructorDetail;

public interface InstructorDao{
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail fInstructorDetailById(int id);
    void deleteInstructorDetailsById(int id);
}