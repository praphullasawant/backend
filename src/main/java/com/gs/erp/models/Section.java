package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_section")
public class Section {

	@Id
	@GeneratedValue
	private Integer id;
	private String sectionName;
	private Boolean active;

	protected Section() {
		
	}
	
	public Section(Integer id, String sectionName, Boolean active) {
		super();
		this.id = id;
		this.sectionName = sectionName;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", sectionName=" + sectionName + ", active=" + active + "]";
	}

}
