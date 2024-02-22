package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.FacultyDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "pad_course_faculty")
@JsonDeserialize(using = FacultyDeserializer.class)
public class Faculty {

	@Id
	@GeneratedValue
	private Integer id;
	private String facultyStream;
	private Boolean checkIfActive;

	protected Faculty() {}
	public Faculty(Integer id, String facultyStream, Boolean checkIfActive) {
		super();
		this.id = id;
		this.facultyStream = facultyStream;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFacultyStream() {
		return facultyStream;
	}

	public void setFacultyStream(String facultyStream) {
		this.facultyStream = facultyStream;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", facultyStream=" + facultyStream + ", checkIfActive=" + checkIfActive + "]";
	}
}
