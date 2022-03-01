package com.matar.education.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matar.education.entity.Grade;
import com.matar.education.entity.Student;


public interface  GradeRepository extends JpaRepository<Grade, Long>{
	
	@Query(value = "SELECT  *     FROM    grades   LIMIT :pageid,:num      ", nativeQuery = true) 
	 public  Optional<List<Grade>>  getGradesByPage(Long pageid,Long num);

}




