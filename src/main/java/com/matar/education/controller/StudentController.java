package com.matar.education.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matar.education.entity.Grade;
import com.matar.education.entity.Student;
import com.matar.education.service.StudentService;
import com.matar.education.service.FilesStorageService;
import com.matar.education.service.GradeService;

@CrossOrigin(origins = "http://localhost:60386")
@RestController
public class StudentController {
	
	private StudentService studentService;
	
	@Autowired
	  FilesStorageService storageService;
	

	public StudentController(StudentService studentServicee) {
		super();
		this.studentService = studentServicee;

	}
	
	
	// handler method to handle list students and return mode and view
	@GetMapping("/studentsImage/")
	public Student getHomePage(@RequestParam("image") String imageName) {
		
		return new Student();
	}
	
	@GetMapping("/")
	public Student getHomePage(Model model) {
		
		return new Student();
	}
	
	  @PostMapping("/students/storeImage")
	  public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file) 
	    {
		   System.out.print("storeImagestoreImagestoreImage ");
	       String message = "";
	      try {
	           storageService.save(file);
	           message = "Uploaded the file successfully: " + file.getOriginalFilename();      
	           return ResponseEntity.status(HttpStatus.OK).body(message);
	       } catch (Exception e) {
	         message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage();
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	   }
	
	
	@PostMapping("/students/storeImage1")
	public void  storeImage() {
		System.out.print("storeImagestoreImagestoreImage ");
	
	}
	
	
	// handler method to handle list students and return mode and view
	@GetMapping("/students/allstudents")
	public List<Student> listStudents(Model model) {
		//model.addAttribute("students", studentService.getAllStudents());
		return  studentService.getAllStudents() ;  
	}
	@GetMapping("/students/studentspage/{pageKey}/{num}/{searchString}")
	public List<Student> getStudentByPage(@PathVariable Long pageKey,@PathVariable Long num,@PathVariable String searchString) {
		System.out.print(pageKey.toString() + searchString);
		
		List l = studentService.getStudentByPage(pageKey,num,searchString).orElseThrow(); 
		System.out.println(l.size());
		return l; 		
	}
	@GetMapping("/students/get/{id}")
	public Student getStudent(@PathVariable Long id) {		
		return studentService.getStudentById(id).orElseThrow(); 
	}
	
	@PostMapping("/students/new")
	public  Student saveStudent(@RequestBody Student student) {
		System.out.println(student);
		
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