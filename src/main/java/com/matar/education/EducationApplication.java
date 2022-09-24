package com.matar.education;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


import com.matar.education.entity.Student;
import com.matar.education.repository.StudentRepository;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class EducationApplication implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;


	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		

		// TODO Auto-generated method stub
		
		/*
		 Student student1 = new Student("Ramesh", "Fadatare", "ramesh@gmail.com");
		 studentRepository.save(student1);
		  
		 Student student2 = new Student("Sanjay", "Jadhav", "sanjay@gmail.com");
		  studentRepository.save(student2);
		 
		 Student student3 = new Student("tony", "stark", "tony@gmail.com");
		 studentRepository.save(student3);*/
	}
	
	
	
	
	
	

}


