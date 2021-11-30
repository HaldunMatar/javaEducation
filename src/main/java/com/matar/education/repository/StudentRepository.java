package com.matar.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matar.education.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long>{

}