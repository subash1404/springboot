package com.example.cruddemo.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao {

    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
    
    // Here I have not used Transactional annotation as we are only just querying from the table and not perform any transactions
    // A transaction is one which does any updates or modifications to the table
    @Override
    public Student findById(Integer id){
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll(){
        TypedQuery<Student> query = entityManager.createQuery("FROM Student",Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByEmail(String email){
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE email = :email",Student.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateStudent(Student theStudent){
        theStudent.setFirstName("Test Name");
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void removeStudent(Student theStudent){    
        // We cant directly remove the Student by passing theStudent to .remove() method because
        // the student that we are going to remove is not managed by the entitiManager (it is detached from the entity manager)   
        // Student found = entityManager.find(Student.class, theStudent.getId()); (or)
        Student found = findById(theStudent.getId());
        entityManager.remove(found);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id){
        Query query = entityManager.createQuery("DELETE FROM Student where id = :id");
        query.setParameter("id", id);
        int numRowsUpdated = query.executeUpdate();
        System.out.println("Num of Rows Deleted :"+numRowsUpdated);
    }
}
