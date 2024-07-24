package com.example.mappingdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mappingdemo.dao.InstructorDao;
import com.example.mappingdemo.entity.Instructor;
import com.example.mappingdemo.entity.InstructorDetail;

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
			deleteInstructorDetailById(instructorDao);
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
}
