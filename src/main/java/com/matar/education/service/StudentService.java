package com.matar.education.service;

import java.util.List;
import java.util.Optional;

import com.matar.education.entity.Student;



public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Optional<Student> getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Long id);
	
	
	
	Optional<Student> findById(Long idStudent);

	void delete(Student student);
	
	Optional<List<Student>>   getStudentByPage(Long pageKey,Long num, String searchString);
	
	
	
}
