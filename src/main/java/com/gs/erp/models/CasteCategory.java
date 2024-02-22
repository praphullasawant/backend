package com.gs.erp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.CasteCategoryDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "acd_master_castecategory")
@JsonDeserialize(using = CasteCategoryDeserializer.class)
public class CasteCategory {
	
	protected CasteCategory() {
		
	}

	@Id
	@GeneratedValue
	private Integer id;
	private String casteCategory;
	private String description;
	private String code;
	private Integer serialNumber;
	private Boolean checkIfActive;
	private Boolean checkIfReserved;
	private Boolean checkIfOpenCategory;
	
	@OneToMany(mappedBy="castecategory")
	@JsonIgnore
	private List<SubCaste> subCaste;

	public CasteCategory(Integer id, String casteCategory, String description, String code, Integer serialNumber,
			Boolean checkIfActive, Boolean checkIfReserved, Boolean checkIfOpenCategory) {
		super();
		this.id = id;
		this.casteCategory = casteCategory;
		this.description = description;
		this.code = code;
		this.serialNumber = serialNumber;
		this.checkIfActive = checkIfActive;
		this.checkIfReserved = checkIfReserved;
		this.checkIfOpenCategory = checkIfOpenCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	public Boolean getCheckIfReserved() {
		return checkIfReserved;
	}

	public void setCheckIfReserved(Boolean checkIfReserved) {
		this.checkIfReserved = checkIfReserved;
	}

	public Boolean getCheckIfOpenCategory() {
		return checkIfOpenCategory;
	}

	public void setCheckIfOpenCategory(Boolean checkIfOpenCategory) {
		this.checkIfOpenCategory = checkIfOpenCategory;
	}

	public List<SubCaste> getSubCaste() {
		return subCaste;
	}

	public void setSubCaste(List<SubCaste> subCaste) {
		this.subCaste = subCaste;
	}

	@Override
	public String toString() {
		return "CasteCategory [id=" + id + ", casteCategory=" + casteCategory + ", description=" + description
				+ ", code=" + code + ", serialNumber=" + serialNumber + ", checkIfActive=" + checkIfActive
				+ ", checkIfReserved=" + checkIfReserved + ", checkIfOpenCategory=" + checkIfOpenCategory + "]";
	}

}
