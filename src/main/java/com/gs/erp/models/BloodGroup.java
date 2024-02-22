package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_bloodgroup")
public class BloodGroup {

	protected BloodGroup() {

	}

	@Id
	@GeneratedValue
	private Integer id;
	private String bloodGroup;
	private Boolean checkIfActive;

	public BloodGroup(Integer id, String bloodGroup, Boolean checkIfActive) {
		super();
		this.id = id;
		this.bloodGroup = bloodGroup;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "BloodGroup [id=" + id + ", bloodGroup=" + bloodGroup + ", checkIfActive=" + checkIfActive + "]";
	}

}
