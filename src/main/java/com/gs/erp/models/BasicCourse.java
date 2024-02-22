package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.BasicCourseDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "pad_fees_basiccourse")
@JsonDeserialize(using = BasicCourseDeserializer.class)
public class BasicCourse {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="faculty_id")
	private Faculty faculty;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private Degree degree;
	
	@ManyToOne
	@JoinColumn(name="cashbook_id")
	private Cashbook cashbook;
	
	private String courseLevel;
	private String basicCourse;
	private String shortName;
	private Long serialNumber;
	private Boolean active;
	
	public BasicCourse() {}

	public BasicCourse(Integer id, Faculty faculty, Degree degree, Cashbook cashbook, String courseLevel,
			String basicCourse, String shortName, Long serialNumber, Boolean active) {
		super();
		this.id = id;
		this.faculty = faculty;
		this.degree = degree;
		this.cashbook = cashbook;
		this.courseLevel = courseLevel;
		this.basicCourse = basicCourse;
		this.shortName = shortName;
		this.serialNumber = serialNumber;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Cashbook getCashbook() {
		return cashbook;
	}

	public void setCashbook(Cashbook cashbook) {
		this.cashbook = cashbook;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getBasicCourse() {
		return basicCourse;
	}

	public void setBasicCourse(String basicCourse) {
		this.basicCourse = basicCourse;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "BasicCourse [id=" + id + ", faculty=" + faculty + ", degree=" + degree + ", cashbook=" + cashbook
				+ ", courseLevel=" + courseLevel + ", basicCourse=" + basicCourse + ", shortName=" + shortName
				+ ", serialNumber=" + serialNumber + ", active=" + active + "]";
	}
	
}
