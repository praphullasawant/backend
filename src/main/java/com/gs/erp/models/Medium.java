package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_medium")
public class Medium {

	@Id
	@GeneratedValue
	private Integer id;
	private String mediumCode;
	private String mediumDescription;
	private Boolean active;
	private Boolean isDefault;
	
	protected Medium() {
		
	}

	public Medium(Integer id, String mediumCode, String mediumDescription, Boolean active, Boolean isDefault) {
		super();
		this.id = id;
		this.mediumCode = mediumCode;
		this.mediumDescription = mediumDescription;
		this.active = active;
		this.isDefault = isDefault;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMediumCode() {
		return mediumCode;
	}

	public void setMediumCode(String mediumCode) {
		this.mediumCode = mediumCode;
	}

	public String getMediumDescription() {
		return mediumDescription;
	}

	public void setMediumDescription(String mediumDescription) {
		this.mediumDescription = mediumDescription;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Medium [id=" + id + ", mediumCode=" + mediumCode + ", mediumDescription=" + mediumDescription
				+ ", active=" + active + ", isDefault=" + isDefault + "]";
	}

}
