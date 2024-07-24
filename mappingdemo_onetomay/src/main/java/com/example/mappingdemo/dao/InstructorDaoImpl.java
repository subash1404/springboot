package com.example.mappingdemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mappingdemo.entity.Course;
import com.example.mappingdemo.entity.Instructor;
import com.example.mappingdemo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class InstructorDaoImpl implements InstructorDao {
    private EntityManager entityManager;

    @Autowired
    public InstructorDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
        System.out.println("Saved to the Database!!");
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
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
        InstructorDetail instructorDetailToDelete = entityManager.find(InstructorDetail.class, id);
        ;

        // remove the associated obj reference and break the bidirectional link
        // Without doing this we cant delete the Instructor detail without deleting the
        // instructor
        instructorDetailToDelete.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetailToDelete);
        System.out.println("Instructor Detail Deleted");
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :id", Course.class);
        query.setParameter("id", id);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager
                .createQuery("select i from Instructor i JOIN FETCH i.courses where i.id = :id", Instructor.class);
        query.setParameter("id", id);
        Instructor foundInstructor = query.getSingleResult();
        return foundInstructor;
    }

    @Override
    @Transactional
    public Instructor updateInstructor(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor foundInstructor = entityManager.find(Instructor.class, id);

        // Instructor foundInstructor = findInstructorById(id);

        //For the first run only the instructor details is set to null
        // Only on the second run the course is actually deleted
        // foundInstructor.setInstructorDetail(null);


        List<Course> courses = foundInstructor.getCourses();
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null); 
        }

        entityManager.remove(foundInstructor); 
    }

    @Override
    public Course findCourseAndReviews(int id){
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id = :id",Course.class);
        query.setParameter("id", id);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where id = :id",Course.class);
        query.setParameter("id", id);
        Course course = query.getSingleResult();
        
        // Not needed here
        // course.setInstructor(null);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course){
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void deleteCouseWithReviews(int id) {
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

}
