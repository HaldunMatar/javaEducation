package com.matar.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "grades")
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(name = "name_en")
	private  String  nameEn;
	
	@Column(name = "name_tr")
	private  String  nameTr;
	@Column(name = "name_ar")
	private  String  nameAr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameAr() {
		return nameAr;
	}
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameTr() {
		return nameTr;
	}
	public void setNameTr(String nameTr) {
		this.nameTr = nameTr;
		
		
	}
	
	

}
