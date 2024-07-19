package com.example.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cruddemo.Dao.StudentDao;
import com.example.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner -> {
			createStudent(studentDao);
			// findById(studentDao);
			// findAll(studentDao);
			// findByEmail(studentDao);
			// updateStudent(studentDao);
			// removeStudent(studentDao);
			// deleteStudent(studentDao);
		};
	}
	Integer id;
	Student foundStudent;
	private void createStudent(StudentDao studentDao){
		Student tempStudent = new Student("Subash","V","subash.v@superops.com");
		studentDao.save(tempStudent);
		id = tempStudent.getId();
		System.out.println(id);
	}
	
	private void findById(StudentDao studentDao){
		foundStudent = studentDao.findById(1);
		System.out.println("Student found :"+foundStudent);
	}

	private void findAll(StudentDao studentDao){
		List<Student> studentList = studentDao.findAll();
		for(Student stud : studentList){
			System.out.println(stud.toString());
		}
	}

	private void findByEmail(StudentDao studentDao){
		List<Student> studentList = studentDao.findByEmail("subash.v@superops.com");
		System.out.println("\n\nStudents with email subash.v@superops.com is");
		for(Student stud : studentList){
			System.out.println(stud.toString());
		}
	}

	private void updateStudent(StudentDao studentDao){
		studentDao.updateStudent(foundStudent);
		System.out.println("Update Student FirstName :"+foundStudent);
	}

	private void removeStudent(StudentDao studentDao){
		foundStudent = studentDao.findById(3);
		System.out.println(foundStudent.toString());
		studentDao.removeStudent(foundStudent);
	}

    private void deleteStudent(StudentDao studentDao) {
		studentDao.deleteStudent(3);
	}
}

