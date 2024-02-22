package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_mothertongue")
public class MotherTongue {

	protected MotherTongue() {

	}

	@Id
	@GeneratedValue
	private Integer id;
	private String motherTongue;
	private Boolean checkIfActive;

	public MotherTongue(Integer id, String motherTongue, Boolean checkIfActive) {
		super();
		this.id = id;
		this.motherTongue = motherTongue;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "MotherTongue [id=" + id + ", motherTongue=" + motherTongue + ", checkIfActive=" + checkIfActive + "]";
	}

}
