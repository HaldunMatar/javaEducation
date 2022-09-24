package com.matar.education.service;

import java.util.List;
import java.util.Optional;

import com.matar.education.entity.Grade;

public interface GradeService {
	
	
	 Optional<List<Grade>> getGradesByPage(Long keyPage, Long num);
	 
	 Optional<Grade>  getGradeById(Long gradeId);
	
	
}


