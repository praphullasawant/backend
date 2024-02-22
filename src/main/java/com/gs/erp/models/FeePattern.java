package com.gs.erp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.CashbookDeserializer;
import com.gs.erp.deserializer.FeePatternDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "pad_course_feepattern")
@JsonDeserialize(using = FeePatternDeserializer.class)
public class FeePattern {

	@Id
	@GeneratedValue
	private Integer id;
	private String feePattern;
	private String feePatternName;
	private Integer noOfSemester;
	
	protected FeePattern() {}
	
	public FeePattern(Integer id, String feePattern, String feePatternName, Integer noOfSemester) {
		super();
		this.id = id;
		this.feePattern = feePattern;
		this.feePatternName = feePatternName;
		this.noOfSemester = noOfSemester;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeePattern() {
		return feePattern;
	}

	public void setFeePattern(String feePattern) {
		this.feePattern = feePattern;
	}

	public String getFeePatternName() {
		return feePatternName;
	}

	public void setFeePatternName(String feePatternName) {
		this.feePatternName = feePatternName;
	}

	public Integer getNoOfSemester() {
		return noOfSemester;
	}

	public void setNoOfSemester(Integer noOfSemester) {
		this.noOfSemester = noOfSemester;
	}

	@Override
	public String toString() {
		return "FeePattern [id=" + id + ", feePattern=" + feePattern + ", feePatternName=" + feePatternName
				+ ", noOfSemester=" + noOfSemester + "]";
	}

}
