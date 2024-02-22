package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.CoursePatternDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pad_course_coursepattern")
@JsonDeserialize(using = CoursePatternDeserializer.class)
public class CoursePattern {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String coursePattern;
	private String coursePatternName;
	private Integer noOfSemester;
	private Boolean checkIfActive;
	
	protected CoursePattern() {}
	public CoursePattern(Integer id, String coursePattern, String coursePatternName, Integer noOfSemester,
			Boolean checkIfActive) {
		super();
		this.id = id;
		this.coursePattern = coursePattern;
		this.coursePatternName = coursePatternName;
		this.noOfSemester = noOfSemester;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoursePattern() {
		return coursePattern;
	}

	public void setCoursePattern(String coursePattern) {
		this.coursePattern = coursePattern;
	}

	public String getCoursePatternName() {
		return coursePatternName;
	}

	public void setCoursePatternName(String coursePatternName) {
		this.coursePatternName = coursePatternName;
	}

	public Integer getNoOfSemester() {
		return noOfSemester;
	}

	public void setNoOfSemester(Integer noOfSemester) {
		this.noOfSemester = noOfSemester;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "CoursePattern [id=" + id + ", coursePattern=" + coursePattern + ", coursePatternName="
				+ coursePatternName + ", noOfSemester=" + noOfSemester + ", checkIfActive=" + checkIfActive + "]";
	}

}
