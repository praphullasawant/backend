package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.TitleDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_title")
@JsonDeserialize(using = TitleDeserializer.class)
public class Title {
	
	protected Title() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nametitle;
	private Boolean checkIfActive;

	public Title(Integer id, String nametitle, Boolean checkIfActive) {
		super();
		this.id = id;
		this.nametitle = nametitle;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNametitle() {
		return nametitle;
	}

	public void setNametitle(String nametitle) {
		this.nametitle = nametitle;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "Title [id=" + id + ", nametitle=" + nametitle + ", checkIfActive=" + checkIfActive + "]";
	}

}
