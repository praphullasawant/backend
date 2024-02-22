package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.GenderDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_gender")
@JsonDeserialize(using = GenderDeserializer.class)
public class Gender {

	@Id
	@GeneratedValue
	private Integer id;
	private String genders;
	private Boolean checkIfActive;
	
	protected Gender() {}
	
	public Gender(Integer id, String genders, Boolean checkIfActive) {
		super();
		this.id = id;
		this.genders = genders;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenders() {
		return genders;
	}

	public void setGenders(String genders) {
		this.genders = genders;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "Gender [id=" + id + ", genders=" + genders + ", checkIfActive=" + checkIfActive + "]";
	}

}
