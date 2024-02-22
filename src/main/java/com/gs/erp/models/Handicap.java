package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "acd_master_handicap")
public class Handicap {
	
	protected Handicap() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String shortName;
	private String description;
	private String percentage;
	private Boolean checkIfActive;

	public Handicap(Integer id, String shortName, String description, String percentage, Boolean checkIfActive) {
		super();
		this.id = id;
		this.shortName = shortName;
		this.description = description;
		this.percentage = percentage;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "Handicap [id=" + id + ", shortName=" + shortName + ", description=" + description + ", percentage="
				+ percentage + ", checkIfActive=" + checkIfActive + "]";
	}

}
