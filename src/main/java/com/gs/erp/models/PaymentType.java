package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_paymenttype")
public class PaymentType {

	@Id
	@GeneratedValue
	private Integer id;
	private String paymentType;
	private String description;
	private Boolean checkIfRte;
	private Boolean checkIfActive;
	
	protected PaymentType() {
		
	}
	
	public PaymentType(Integer id, String paymentType, String description, Boolean checkIfRte, Boolean checkIfActive) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.description = description;
		this.checkIfRte = checkIfRte;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCheckIfRte() {
		return checkIfRte;
	}

	public void setCheckIfRte(Boolean checkIfRte) {
		this.checkIfRte = checkIfRte;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "PaymentType [id=" + id + ", paymentType=" + paymentType + ", description=" + description
				+ ", checkIfRte=" + checkIfRte + ", checkIfActive=" + checkIfActive + "]";
	}
	
}
