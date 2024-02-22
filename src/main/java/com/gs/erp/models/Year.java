package com.gs.erp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "pad_course_year")
public class Year {

	@Id
	@GeneratedValue
	private Integer id;
	private String year;
	private String yourDefination;
	
	@OneToMany(mappedBy="year")
	@JsonIgnore
	private List<Semester> semester;	

	public Year(Integer id, String year, String yourDefination) {
		super();
		this.id = id;
		this.year = year;
		this.yourDefination = yourDefination;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYourDefination() {
		return yourDefination;
	}

	public void setYourDefination(String yourDefination) {
		this.yourDefination = yourDefination;
	}

	public List<Semester> getSemester() {
		return semester;
	}

	public void setSemester(List<Semester> semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "Year [id=" + id + ", year=" + year + ", yourDefination=" + yourDefination + "]";
	}

}
