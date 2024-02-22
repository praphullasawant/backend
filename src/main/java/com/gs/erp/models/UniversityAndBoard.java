package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_univandboard")
public class UniversityAndBoard {

	protected UniversityAndBoard () {
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	private String universityBoardCode;
	private String universityBoardName;
	private Boolean checkIfHomeUniversity;
	private Boolean checkIfActive;

	public UniversityAndBoard(Integer id, String universityBoardCode, String universityBoardName,
			Boolean checkIfHomeUniversity, Boolean checkIfActive) {
		super();
		this.id = id;
		this.universityBoardCode = universityBoardCode;
		this.universityBoardName = universityBoardName;
		this.checkIfHomeUniversity = checkIfHomeUniversity;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUniversityBoardCode() {
		return universityBoardCode;
	}

	public void setUniversityBoardCode(String universityBoardCode) {
		this.universityBoardCode = universityBoardCode;
	}

	public String getUniversityBoardName() {
		return universityBoardName;
	}

	public void setUniversityBoardName(String universityBoardName) {
		this.universityBoardName = universityBoardName;
	}

	public Boolean getCheckIfHomeUniversity() {
		return checkIfHomeUniversity;
	}

	public void setCheckIfHomeUniversity(Boolean checkIfHomeUniversity) {
		this.checkIfHomeUniversity = checkIfHomeUniversity;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "UniversityAndBoard [id=" + id + ", universityBoardCode=" + universityBoardCode
				+ ", universityBoardName=" + universityBoardName + ", checkIfHomeUniversity=" + checkIfHomeUniversity
				+ ", checkIfActive=" + checkIfActive + "]";
	}

}
