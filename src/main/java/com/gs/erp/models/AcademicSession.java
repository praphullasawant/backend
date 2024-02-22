package com.gs.erp.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_academicsession")
public class AcademicSession {

	@Id
	@GeneratedValue
	private Integer id;
	private String sessionName;
	private String shortName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean active;
	private Boolean oldSession;
	
	protected AcademicSession() {
		
	}

	public AcademicSession(Integer id, String sessionName, String shortName, LocalDate startDate, LocalDate endDate,
			Boolean active, Boolean oldSession) {
		super();
		this.id = id;
		this.sessionName = sessionName;
		this.shortName = shortName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
		this.oldSession = oldSession;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getOldSession() {
		return oldSession;
	}

	public void setOldSession(Boolean oldSession) {
		this.oldSession = oldSession;
	}

	@Override
	public String toString() {
		return "AcademicSession [id=" + id + ", sessionName=" + sessionName + ", shortName=" + shortName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", active=" + active + ", oldSession="
				+ oldSession + "]";
	}
}
