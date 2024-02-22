package com.gs.erp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "acd_master_subcaste")
public class SubCaste {

	@Id
	@GeneratedValue
	private Integer id;
	private String subcaste;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private CasteCategory castecategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubcaste() {
		return subcaste;
	}

	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}
	
	public CasteCategory getCastecategory() {
		return castecategory;
	}

	public void setCastecategory(CasteCategory castecategory) {
		this.castecategory = castecategory;
	}

	@Override
	public String toString() {
		return "SubCaste [id=" + id + ", subcaste=" + subcaste + "]";
	}

}
