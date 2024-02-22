package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_nationality")
public class Nationality {
	protected Nationality() {
		
	}

	@Id
	@GeneratedValue
	private Integer id;
	private String nationality;
	private Boolean checkIfActive;

	public Nationality(Integer id, String nationality, Boolean checkIfActive) {
		super();
		this.id = id;
		this.nationality = nationality;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "Nationality [id=" + id + ", nationality=" + nationality + ", checkIfActive=" + checkIfActive + "]";
	}

}
