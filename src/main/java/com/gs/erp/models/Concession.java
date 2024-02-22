package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="acd_master_concession")
public class Concession {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String ShortName;
	private String ConDescription;
	private Integer Percentage;
	private Boolean Active;
	
	protected Concession() {
		
	}
	
	public Concession(Integer id, String shortName, String conDescription, Integer percentage, Boolean active) {
		super();
		this.id = id;
		this.ShortName = shortName;
		this.ConDescription = conDescription;
		this.Percentage = percentage;
		this.Active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public String getConDescription() {
		return ConDescription;
	}

	public void setConDescription(String conDescription) {
		ConDescription = conDescription;
	}

	public Integer getPercentage() {
		return Percentage;
	}

	public void setPercentage(Integer percentage) {
		Percentage = percentage;
	}

	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}

	@Override
	public String toString() {
		return "Concession [id=" + id + ", ShortName=" + ShortName + ", ConDescription=" + ConDescription
				+ ", Percentage=" + Percentage + ", Active=" + Active + "]";
	}
	
}
