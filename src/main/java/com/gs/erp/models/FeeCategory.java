package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="acd_master_feecategory")
public class FeeCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String PaymentType;
	private String Description;
	private Boolean ChechIfRte;
	private Boolean CheckIfActive;
	
	public FeeCategory(Integer id, String paymentType, String description, Boolean chechIfRte, Boolean checkIfActive) {
		super();
		this.id = id;
		PaymentType = paymentType;
		Description = description;
		ChechIfRte = chechIfRte;
		CheckIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Boolean getChechIfRte() {
		return ChechIfRte;
	}

	public void setChechIfRte(Boolean chechIfRte) {
		ChechIfRte = chechIfRte;
	}

	public Boolean getCheckIfActive() {
		return CheckIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		CheckIfActive = checkIfActive;
	}

	@Override
	public String toString() {
		return "FeeCategory [id=" + id + ", PaymentType=" + PaymentType + ", Description=" + Description
				+ ", ChechIfRte=" + ChechIfRte + ", CheckIfActive=" + CheckIfActive + "]";
	}
	
}
