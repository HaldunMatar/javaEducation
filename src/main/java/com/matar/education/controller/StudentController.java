package com.matar.education.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matar.education.entity.Student;
import com.matar.education.service.StudentService;



@RestController
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	// handler method to handle list students and return mode and view
	@GetMapping("/")
	public Student getHomePage(Model model) {
		
		return new Student();
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/students/allstudents")
	public List<Student> listStudents(Model model) {
		//model.addAttribute("students", studentService.getAllStudents());
		return  studentService.getAllStudents() ;
	}
	@GetMapping("/students/get/{id}")
	public Student getStudent(@PathVariable Long id) {		
		return studentService.getStudentById(id).orElseThrow(); 
	}
	
	@PostMapping("/students/new")
	public  Student saveStudent(@RequestBody Student student) {
		
		return  studentService.saveStudent(student);
		
	}
	
	@PutMapping("/student/update/{id}")
	
	public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student studentDetails){
		
		Student student = studentService.findById(id).orElseThrow();
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setEmail(studentDetails.getEmail());
		Student  updatedstudent= studentService.saveStudent(student);
		return ResponseEntity.ok(updatedstudent);
		
	}
	
	// delete employee rest api
	@DeleteMapping("/student/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Student student = studentService.findById(id).orElseThrow();
		
		studentService.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted ok ", Boolean.TRUE);
		response.put("tesst ", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	/*@PostMapping("/students")
	public Strinnt(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}*/
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id).orElseThrow();
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
}