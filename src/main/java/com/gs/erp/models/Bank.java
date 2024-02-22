package com.gs.erp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "acd_master_bank")
public class Bank {

	@Id
	@GeneratedValue
	private Integer id;
	private String bankCode;
	private String bankName;
	private String bankAddress;
	private Boolean checkIfActive;
	
	@OneToMany(mappedBy="bank")
	@JsonIgnore
	private List<BankAcccount> bankAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cashbook cashbook;
	
	public Bank() {
		
	}

	public Bank(Integer id, String bankCode, String bankName, String bankAddress, Boolean checkIfActive) {
		super();
		this.id = id;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.bankAddress = bankAddress;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}
	
	public List<BankAcccount> getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(List<BankAcccount> bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public Cashbook getCashbook() {
		return cashbook;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", bankCode=" + bankCode + ", bankName=" + bankName + ", bankAddress=" + bankAddress
				+ ", checkIfActive=" + checkIfActive + "]";
	}

}
