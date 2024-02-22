package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_studenttype")
public class StudentType {

	@Id
	@GeneratedValue
	private Integer id;
	private String studentType;
	private Boolean checkIfActive;
	
	protected StudentType() {
		
	}
	
	public StudentType(Integer id, String studentType, Boolean checkIfActive) {
		super();
		this.id = id;
		this.studentType = studentType;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "StudentType [id=" + id + ", studentType=" + studentType + ", checkIfActive=" + checkIfActive + "]";
	}
	
	
}
