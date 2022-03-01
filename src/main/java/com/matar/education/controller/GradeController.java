package com.matar.education.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.matar.education.entity.Grade;
import com.matar.education.service.GradeService;
@CrossOrigin(origins = "http://localhost:60386")
@RestController
public class GradeController {
	
	private GradeService gradeService ;
	
	
	public  GradeController(GradeService gradeService) {		
		super();		
		this.gradeService=gradeService;
	}
	
	
	
	@GetMapping("/grades/gradespage/{pageKey}/{num}")
	public List<Grade> getGradeByPage(@PathVariable Long pageKey,@PathVariable Long num) {
		System.out.println("getGradeByPage");
		List<Grade> temp= gradeService.getGradesByPage(pageKey, num).orElseThrow();  
		
		System.out.println(temp.size());
		
		return gradeService.getGradesByPage(pageKey, num).orElseThrow(); 
	}
	

	
}
