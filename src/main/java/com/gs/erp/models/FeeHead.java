package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "pad_course_feehead")
public class FeeHead {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="receiptbook_id")
	private Cashbook cashbook;
	
	private String headDescription;
	private String shortName;
	private Boolean otherFeeHead;
	private Boolean installmentFeeHead;
	private Boolean excessFee;
	private Boolean goiScholarship;
	private Integer serialNo;
	private Boolean gstApplicable;
	private Boolean active;

	protected FeeHead() {}

	public FeeHead(Integer id, Cashbook cashbook, String headDescription, String shortName, Boolean otherFeeHead,
			Boolean installmentFeeHead, Boolean excessFee, Boolean goiScholarship, Integer serialNo,
			Boolean gstApplicable, Boolean active) {
		super();
		this.id = id;
		this.cashbook = cashbook;
		this.headDescription = headDescription;
		this.shortName = shortName;
		this.otherFeeHead = otherFeeHead;
		this.installmentFeeHead = installmentFeeHead;
		this.excessFee = excessFee;
		this.goiScholarship = goiScholarship;
		this.serialNo = serialNo;
		this.gstApplicable = gstApplicable;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cashbook getCashbook() {
		return cashbook;
	}

	public void setCashbook(Cashbook cashbook) {
		this.cashbook = cashbook;
	}

	public String getHeadDescription() {
		return headDescription;
	}

	public void setHeadDescription(String headDescription) {
		this.headDescription = headDescription;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Boolean getOtherFeeHead() {
		return otherFeeHead;
	}

	public void setOtherFeeHead(Boolean otherFeeHead) {
		this.otherFeeHead = otherFeeHead;
	}

	public Boolean getInstallmentFeeHead() {
		return installmentFeeHead;
	}

	public void setInstallmentFeeHead(Boolean installmentFeeHead) {
		this.installmentFeeHead = installmentFeeHead;
	}

	public Boolean getExcessFee() {
		return excessFee;
	}

	public void setExcessFee(Boolean excessFee) {
		this.excessFee = excessFee;
	}

	public Boolean getGoiScholarship() {
		return goiScholarship;
	}

	public void setGoiScholarship(Boolean goiScholarship) {
		this.goiScholarship = goiScholarship;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public Boolean getGstApplicable() {
		return gstApplicable;
	}

	public void setGstApplicable(Boolean gstApplicable) {
		this.gstApplicable = gstApplicable;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "FeeHead [id=" + id + ", cashbook=" + cashbook + ", headDescription=" + headDescription + ", shortName="
				+ shortName + ", otherFeeHead=" + otherFeeHead + ", installmentFeeHead=" + installmentFeeHead
				+ ", excessFee=" + excessFee + ", goiScholarship=" + goiScholarship + ", serialNo=" + serialNo
				+ ", gstApplicable=" + gstApplicable + ", active=" + active + "]";
	}

}
