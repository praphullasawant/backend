package com.gs.erp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "pad_course_semester")
public class Semester {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer semesterNumber;
	private String semesterDescription;
	private Boolean checkIfActive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Year year;

	public Semester(Integer id, Integer semesterNumber, String semesterDescription, Boolean checkIfActive) {
		super();
		this.id = id;
		this.semesterNumber = semesterNumber;
		this.semesterDescription = semesterDescription;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(Integer semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	public String getSemesterDescription() {
		return semesterDescription;
	}

	public void setSemesterDescription(String semesterDescription) {
		this.semesterDescription = semesterDescription;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Semester [id=" + id + ", semesterNumber=" + semesterNumber + ", semesterDescription="
				+ semesterDescription + ", checkIfActive=" + checkIfActive + "]";
	}

}
