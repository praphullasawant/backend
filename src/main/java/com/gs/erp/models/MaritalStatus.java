package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_maritalstatus")
public class MaritalStatus {

	protected MaritalStatus() {

	}

	@Id
	@GeneratedValue
	private Integer id;
	private String maritalStatus;
	private Boolean checkIfActive;

	public MaritalStatus(Integer id, String maritalStatus, Boolean checkIfActive) {
		super();
		this.id = id;
		this.maritalStatus = maritalStatus;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "MaritalStatus [id=" + id + ", maritalStatus=" + maritalStatus + ", checkIfActive=" + checkIfActive
				+ "]";
	}

}
