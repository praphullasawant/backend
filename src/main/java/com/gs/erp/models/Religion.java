package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_religion")
public class Religion {
	protected Religion() {
		
	}
	@Id
	@GeneratedValue
	private Integer id;
	private String religion;
	private Boolean checkIfActive;
	private Boolean checkIfMinority;

	public Religion(Integer id, String religion, Boolean checkIfActive, Boolean checkIfMinority) {
		super();
		this.id = id;
		this.religion = religion;
		this.checkIfActive = checkIfActive;
		this.checkIfMinority = checkIfMinority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	public Boolean getCheckIfMinority() {
		return checkIfMinority;
	}

	public void setCheckIfMinority(Boolean checkIfMinority) {
		this.checkIfMinority = checkIfMinority;
	}

	@Override
	public String toString() {
		return "Religion [id=" + id + ", religion=" + religion + ", checkIfActive=" + checkIfActive
				+ ", checkIfMinority=" + checkIfMinority + "]";
	}

}
