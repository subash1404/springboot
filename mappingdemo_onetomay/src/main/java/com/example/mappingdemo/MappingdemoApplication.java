package com.example.mappingdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mappingdemo.dao.InstructorDao;
import com.example.mappingdemo.entity.Course;
import com.example.mappingdemo.entity.Instructor;
import com.example.mappingdemo.entity.InstructorDetail;
import com.example.mappingdemo.entity.Review;

@SpringBootApplication
public class MappingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingdemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDao instructorDao){
		return runner ->{
			// createInstructor(instructorDao);
			// findInstructorById(instructorDao);
			// deleteInstructorById(instructorDao);
			// findInstructorDetailById(instructorDao);
			// deleteInstructorDetailById(instructorDao);
			// createInstructorWithCourses(instructorDao);
			// findInstructorWithCourses(instructorDao);
			// findInstructorWithCoursesJoinFetch(instructorDao);
			// updateInstructor(instructorDao);
			// deleteInstructor(instructorDao);
			// deleteCourse(instructorDao);
			// saveCouse(instructorDao);
			// findCourseWithReviews(instructorDao);
			deleteCourseWithReviews(instructorDao);
		};
	}

    private void createInstructor(InstructorDao instructorDao) {
        Instructor tempInstructor = new Instructor("Subash","Velu","subash@gmail.com");
		InstructorDetail tempDetail = new InstructorDetail("yt_channel", "hobby");
		tempInstructor.setInstructorDetail(tempDetail);
		instructorDao.save(tempInstructor);
    }

    private void findInstructorById(InstructorDao instructorDao) {
        int id = 1;
		Instructor foundInstructor = instructorDao.findInstructorById(id);
		System.out.println("\nFound Instructor :"+foundInstructor.toString());
		System.out.println("\nFound Instructor Details :"+foundInstructor.getInstructorDetail());
    }

    private void deleteInstructorById(InstructorDao instructorDao) {
		int id = 1;
		instructorDao.deleteInstructorById(id);
    }

    private void findInstructorDetailById(InstructorDao instructorDao) {
        int id = 2;
		InstructorDetail foundInstructorDetail = instructorDao.fInstructorDetailById(id);
		System.out.println("Found InstructorDetail detail :"+foundInstructorDetail.toString());
		System.out.println("Found Instructor Detail :"+foundInstructorDetail.getInstructor());
    }

    private void deleteInstructorDetailById(InstructorDao instructorDao) {
        int id = 3;
		instructorDao.deleteInstructorDetailsById(id);
    }

    private void createInstructorWithCourses(InstructorDao instructorDao) {
        Instructor tempInstructor = new Instructor("Subash","Velu","subash@gmail.com");
		InstructorDetail tempDetail = new InstructorDetail("yt_channel", "hobby");
		tempInstructor.setInstructorDetail(tempDetail);
		
		Course course1 = new Course("Course1");
		Course course2 = new Course("Course2");
		Course course3 = new Course("Course3");

		tempInstructor.add(course1);
		tempInstructor.add(course2);
		tempInstructor.add(course3);
		
		// This will also save the courses and the instructor details because of the cascading
		instructorDao.save(tempInstructor);
		System.out.println("Saved the instrcutor");
    }

    private void findInstructorWithCourses(InstructorDao instructorDao) {
		int id = 2;
		Instructor tempInstructor = instructorDao.findInstructorById(id);
		System.out.println("Temp Instructor: "+tempInstructor);
		List<Course> courses = instructorDao.findCoursesByInstructorId(id);
		System.out.println("Courses Associated: " + courses);
    }

    private void findInstructorWithCoursesJoinFetch(InstructorDao instructorDao) {
		int id =2;
        Instructor foundInstructor = instructorDao.findInstructorByIdJoinFetch(id);
		System.out.println("Temp Instructor: " + foundInstructor);

		// As we are using join fetch the courses associated with the instructors will also be found
		List<Course> courses = foundInstructor.getCourses();
		System.out.println("Courses Associated: " + courses);
    }

    private void updateInstructor(InstructorDao instructorDao) {
        int id = 2;
		Instructor foundInstructor = instructorDao.findInstructorById(id);
		foundInstructor.setFirstName("TEST2");
		instructorDao.updateInstructor(foundInstructor);
    }

    private void deleteInstructor(InstructorDao instructorDao) {
        int id =5;
		instructorDao.deleteInstructor(id);
    }

    private void deleteCourse(InstructorDao instructorDao) {
        int id = 12;
		instructorDao.deleteCourse(id);
		System.out.println("Course deleted");
    }

    private void saveCouse(InstructorDao instructorDao) {
        Course course = new Course("course1");

		
		course.addReview(new Review("Comment for course 1"));
		course.addReview(new Review("Comment for course 2"));
		course.addReview(new Review("Comment for course 3"));

		instructorDao.saveCourse(course);
    }

    private void findCourseWithReviews(InstructorDao instructorDao) {
		int id = 10;
		Course course = instructorDao.findCourseAndReviews(id);
		System.out.println("Found Course: " + course);
		System.out.println("Reviews associated with the course: " + course.getReviews());
    }

    private void deleteCourseWithReviews(InstructorDao instructorDao) {
        int id = 10;
		instructorDao.deleteCouseWithReviews(id);
    }
}
