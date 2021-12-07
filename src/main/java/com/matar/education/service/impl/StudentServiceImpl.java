package com.matar.education.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.matar.education.entity.Student;
import com.matar.education.repository.StudentRepository;
import com.matar.education.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
    
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}

	@Override
	public Optional<Student> findById(Long idStudent) {
		// TODO Auto-generated method stub
		return studentRepository.findById(idStudent) ;
	}

	@Override
	public void delete(Student student) {
		 studentRepository.deleteById(student.getId());
		
	}

}