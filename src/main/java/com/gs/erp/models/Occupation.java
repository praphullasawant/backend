package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_occupation")
public class Occupation {

	@Id
	@GeneratedValue
	private Integer id;
	private String occupation;
	private Boolean active;

	protected Occupation() {
		
	}
	
	public Occupation(Integer id, String occupation, Boolean active) {
		super();
		this.id = id;
		this.occupation = occupation;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Occupation [id=" + id + ", occupation=" + occupation + ", active=" + active + "]";
	}

}
