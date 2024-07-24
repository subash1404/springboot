package com.example.mappingdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mappingdemo.entity.Instructor;
import com.example.mappingdemo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class InstructorDaoImpl implements InstructorDao {
    private EntityManager entityManager;

    @Autowired
    public InstructorDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor){
        entityManager.persist(instructor);
        System.out.println("Saved to the Database!!");
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructortoDelete = entityManager.find(Instructor.class, id);
        entityManager.remove(instructortoDelete);
        System.out.println("Deleted from the database");
    }

    @Override
    public InstructorDetail fInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int id) {
        InstructorDetail instructorDetailToDelete = entityManager.find(InstructorDetail.class, id);;
        
        // remove the associated obj reference and break the bidirectional link
        // Without doing this we cant delete the Instructor detail without deleting the instructor  
        instructorDetailToDelete.getInstructor().setInstructorDetail(null);
        
        entityManager.remove(instructorDetailToDelete);
        System.out.println("Instructor Detail Deleted");
    }
}
