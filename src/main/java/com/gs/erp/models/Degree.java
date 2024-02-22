package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.DegreeDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "pad_course_degree")
@JsonDeserialize(using = DegreeDeserializer.class)
public class Degree {

	@Id
	@GeneratedValue
	private Integer id;
	private String degreeDiploma;
	private String degreeDiplomaName;
	private Boolean checkIfActive;

	protected Degree() {}
	
	public Degree(Integer id, String degreeDiploma, String degreeDiplomaName, Boolean checkIfActive) {
		super();
		this.id = id;
		this.degreeDiploma = degreeDiploma;
		this.degreeDiplomaName = degreeDiplomaName;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDegreeDiploma() {
		return degreeDiploma;
	}

	public void setDegreeDiploma(String degreeDiploma) {
		this.degreeDiploma = degreeDiploma;
	}

	public String getDegreeDiplomaName() {
		return degreeDiplomaName;
	}

	public void setDegreeDiplomaName(String degreeDiplomaName) {
		this.degreeDiplomaName = degreeDiplomaName;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "Degree [id=" + id + ", degreeDiploma=" + degreeDiploma + ", degreeDiplomaName=" + degreeDiplomaName
				+ ", checkIfActive=" + checkIfActive + "]";
	}

}
