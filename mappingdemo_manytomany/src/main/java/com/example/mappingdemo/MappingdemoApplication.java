package com.example.mappingdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mappingdemo.dao.InstructorDao;
import com.example.mappingdemo.entity.Course;
import com.example.mappingdemo.entity.Student;

@SpringBootApplication
public class MappingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingdemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDao instructorDao){
		return runner ->{
			System.out.println("Hello Word");
			// saveStudentWithCourses(instructorDao);
			// findCourseAndStudentsByCourseId(instructorDao);
			// addMoreCourses(instructorDao);
			// findCourseAndStudentsByStudentId(instructorDao);
			// deleteCourse(instructorDao);
			deleteStudent(instructorDao);
		};
	}

	private void saveStudentWithCourses(InstructorDao instructorDao) {
		Course course = new Course("Course 1");

		Student stud1 = new Student("subash","v","email");
		Student stud2 = new Student("dinesh","v","email");
		Student stud3 = new Student("naveen","v","email");

		course.addStudent(stud1);
		course.addStudent(stud2);
		course.addStudent(stud3);

		instructorDao.saveCourse(course);
	}

    private void findCourseAndStudentsByCourseId(InstructorDao instructorDao) {
        int id = 10;
		Course course = instructorDao.findCourseAndStudentsByCourseId(id);
		System.out.println("course: " + course);
		System.out.println("Students associcated with the course: " + course.getStudents());
		System.out.println("Done !!!");
    }

    private void findCourseAndStudentsByStudentId(InstructorDao instructorDao) {
        int id = 1;
		Student stud = instructorDao.findCourseAndStudentsByStudentId(id);
		System.out.println("Student: " + stud);
		System.out.println("Courses associcated with the student: " + stud.getCourses());
		System.out.println("Done !!!");
    }

    private void addMoreCourses(InstructorDao instructorDao) {
        int id = 1;
		Student stud = instructorDao.findCourseAndStudentsByStudentId(id);

		Course course1 = new Course("Course10");
		Course course2 = new Course("Course11");

		stud.addCourse(course1);
		stud.addCourse(course2);

		instructorDao.update(stud);
    }

    private void deleteCourse(InstructorDao instructorDao) {
        int id = 10;
		instructorDao.deleteCourse(id);
		System.out.println("Course Deleted");
    }

    private void deleteStudent(InstructorDao instructorDao) {
        int id = 1;
		instructorDao.deleteStudent(id);
		System.out.println("Student deleted");
    }
}
