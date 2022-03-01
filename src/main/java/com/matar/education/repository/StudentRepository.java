package com.matar.education.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matar.education.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long>{

//@Query("SELECT  q   FROM   Question  q    where   q.subject != 'chemistry'  and   q.level = :level  ")
	
@Query(value = "SELECT *  FROM   students  where "
	
		+ "  (   students.first_name  like %:searchString%  )  LIMIT :pageid,:num      ", 
nativeQuery = true) 

 public  Optional<List<Student>>  getStudentByPageSeach(Long pageid,Long num, String searchString);






@Query(value = " SELECT *   FROM   students   LIMIT :pageid , :num      ", nativeQuery = true)
 public Optional<List<Student>> getStudentByPage(Long pageid, Long num);





}
