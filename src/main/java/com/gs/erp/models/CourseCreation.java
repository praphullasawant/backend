package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "pad_fees_coursecreation")
public class CourseCreation {
	
	@Id
	@GeneratedValue
	private Integer id;	
	
	@ManyToOne
	@JoinColumn(name="basicCourse_id")
	private BasicCourse basicCourse;
	
	private Integer duration;
	
	@ManyToOne
	@JoinColumn(name="coursePattern_id")
	private CoursePattern coursePattern;
	
	@ManyToOne
	@JoinColumn(name="feePattern_id")
	private FeePattern feePattern;
	
	private Boolean checkIfActive;
	
	public CourseCreation() {}

	public CourseCreation(Integer id, BasicCourse basicCourse, Integer duration, CoursePattern coursePattern,
			FeePattern feePattern, Boolean checkIfActive) {
		super();
		this.id = id;
		this.basicCourse = basicCourse;
		this.duration = duration;
		this.coursePattern = coursePattern;
		this.feePattern = feePattern;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasicCourse getBasicCourse() {
		return basicCourse;
	}

	public void setBasicCourse(BasicCourse basicCourse) {
		this.basicCourse = basicCourse;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public CoursePattern getCoursePattern() {
		return coursePattern;
	}

	public void setCoursePattern(CoursePattern coursePattern) {
		this.coursePattern = coursePattern;
	}

	public FeePattern getFeePattern() {
		return feePattern;
	}

	public void setFeePattern(FeePattern feePattern) {
		this.feePattern = feePattern;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "CourseCreation [id=" + id + ", basicCourse=" + basicCourse + ", duration=" + duration
				+ ", coursePattern=" + coursePattern + ", feePattern=" + feePattern + ", checkIfActive=" + checkIfActive
				+ "]";
	}

}
