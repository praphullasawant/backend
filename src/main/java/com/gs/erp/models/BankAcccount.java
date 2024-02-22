package com.gs.erp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "acd_master_bankaccount")
public class BankAcccount {

	@Id
	@GeneratedValue
	private Integer id;
	private String bankName;
	private String accountNumber;
	private Boolean checkIfActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Bank bank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cashbook cashbook;
	
	protected BankAcccount() {
		
	}
	public BankAcccount(Integer id, String bankName, String accountNumber, Boolean checkIfActive) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Cashbook getCashbook() {
		return cashbook;
	}
	@Override
	public String toString() {
		return "BankAcccount [id=" + id + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", checkIfActive=" + checkIfActive + "]";
	}

}
