package com.gs.erp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gs.erp.deserializer.CashbookDeserializer;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "pad_course_cashbook")
@JsonDeserialize(using = CashbookDeserializer.class)
public class Cashbook {

	@Id
	@GeneratedValue
	private Integer id;
	private String receiptBookCode;
	private String receiptBookName;
	private String cashReceiptNo;
	private String bankReceiptNo;
	private Integer documentReceiptNo;
	private String hsnsacNo;
	private String receiptBookType;
	private Integer srNo;
	private Boolean checkIfGstApplicable;
	private Boolean checkIfActive;
	
	@OneToMany(mappedBy="cashbook")
	@JsonIgnore
	private List<Bank> bankName;
	
	@JsonIgnore
	@OneToMany(mappedBy="cashbook")
	private List<BankAcccount> bankAccountNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private FeeHead feehead;

	protected Cashbook() {}
	
	public Cashbook(Integer id, String receiptBookCode, String receiptBookName, String cashReceiptNo,
			String bankReceiptNo, Integer documentReceiptNo, String hsnsacNo, String receiptBookType, Integer srNo,
			Boolean checkIfGstApplicable, Boolean checkIfActive) {
		super();
		this.id = id;
		this.receiptBookCode = receiptBookCode;
		this.receiptBookName = receiptBookName;
		this.cashReceiptNo = cashReceiptNo;
		this.bankReceiptNo = bankReceiptNo;
		this.documentReceiptNo = documentReceiptNo;
		this.hsnsacNo = hsnsacNo;
		this.receiptBookType = receiptBookType;
		this.srNo = srNo;
		this.checkIfGstApplicable = checkIfGstApplicable;
		this.checkIfActive = checkIfActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReceiptBookCode() {
		return receiptBookCode;
	}

	public void setReceiptBookCode(String receiptBookCode) {
		this.receiptBookCode = receiptBookCode;
	}

	public String getReceiptBookName() {
		return receiptBookName;
	}

	public void setReceiptBookName(String receiptBookName) {
		this.receiptBookName = receiptBookName;
	}

	public String getCashReceiptNo() {
		return cashReceiptNo;
	}

	public void setCashReceiptNo(String cashReceiptNo) {
		this.cashReceiptNo = cashReceiptNo;
	}

	public String getBankReceiptNo() {
		return bankReceiptNo;
	}

	public void setBankReceiptNo(String bankReceiptNo) {
		this.bankReceiptNo = bankReceiptNo;
	}

	public Integer getDocumentReceiptNo() {
		return documentReceiptNo;
	}

	public void setDocumentReceiptNo(Integer documentReceiptNo) {
		this.documentReceiptNo = documentReceiptNo;
	}

	public String getHsnsacNo() {
		return hsnsacNo;
	}

	public void setHsnsacNo(String hsnsacNo) {
		this.hsnsacNo = hsnsacNo;
	}

	public String getReceiptBookType() {
		return receiptBookType;
	}

	public void setReceiptBookType(String receiptBookType) {
		this.receiptBookType = receiptBookType;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public Boolean getCheckIfGstApplicable() {
		return checkIfGstApplicable;
	}

	public void setCheckIfGstApplicable(Boolean checkIfGstApplicable) {
		this.checkIfGstApplicable = checkIfGstApplicable;
	}

	public Boolean getCheckIfActive() {
		return checkIfActive;
	}

	public void setCheckIfActive(Boolean checkIfActive) {
		this.checkIfActive = checkIfActive;
	}

	public List<Bank> getBankName() {
		return bankName;
	}

	public void setBankName(List<Bank> bankName) {
		this.bankName = bankName;
	}

	public List<BankAcccount> getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(List<BankAcccount> bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public FeeHead getFeehead() {
		return feehead;
	}

	@Override
	public String toString() {
		return "Cashbook [id=" + id + ", receiptBookCode=" + receiptBookCode + ", receiptBookName=" + receiptBookName
				+ ", cashReceiptNo=" + cashReceiptNo + ", bankReceiptNo=" + bankReceiptNo + ", documentReceiptNo="
				+ documentReceiptNo + ", hsnsacNo=" + hsnsacNo + ", receiptBookType=" + receiptBookType + ", srNo="
				+ srNo + ", checkIfGstApplicable=" + checkIfGstApplicable + ", checkIfActive=" + checkIfActive + "]";
	}

}
