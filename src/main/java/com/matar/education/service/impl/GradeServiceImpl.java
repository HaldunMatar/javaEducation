package com.matar.education.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.matar.education.entity.Grade;
import com.matar.education.entity.Student;
import com.matar.education.repository.GradeRepository;
import com.matar.education.repository.StudentRepository;
import com.matar.education.service.GradeService;
import com.matar.education.service.StudentService;




	

	@Service
public class GradeServiceImpl implements GradeService {
		
		private GradeRepository gradeRepository;
		
		public GradeServiceImpl(GradeRepository gradeRepository) {
			super();
			this.gradeRepository = gradeRepository;
			
		}

		@Override
		public Optional <List<Grade>> getGradesByPage(Long keyPage, Long num) {
			// TODO Auto-generated method stub
			return gradeRepository.getGradesByPage(keyPage, num); 
		}

		@Override
		public Optional<Grade> getGradeById(Long Id) {
			// TODO Auto-generated method stub
			System.out.println("  @Override  getGradeById ");
			System.out.println(Id.doubleValue());
			//System.out.println(gradeRepository.findById((long) 993));
			return gradeRepository.findById(Id) ;
		}
		


	


	
		
		
	
		



}
