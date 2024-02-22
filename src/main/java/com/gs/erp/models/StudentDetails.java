package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "acd_transaction_studentadmission")
public class StudentDetails {
	
	protected StudentDetails() {}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;
	
	private String motherName;
	private String surname;
	private String umn;
	private String firstName;
	private String fatherName;
	private String middleName;
	private String ufn;
	private String uName;
	private Long enrollmentNumber;
	
	@ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
	
	private Long mobile;
	
	@ManyToOne
    @JoinColumn(name = "casteCategory_id")
    private CasteCategory casteCategory;
	
	private String email;
	private String remark;
	
	public StudentDetails(Integer id, Title title, String motherName, String surname, String umn, String firstName,
			String fatherName, String middleName, String ufn, String uName, Long enrollmentNumber, Gender gender,
			Long mobile, CasteCategory casteCategory, String email, String remark) {
		super();
		this.id = id;
		this.title = title;
		this.motherName = motherName;
		this.surname = surname;
		this.umn = umn;
		this.firstName = firstName;
		this.fatherName = fatherName;
		this.middleName = middleName;
		this.ufn = ufn;
		this.uName = uName;
		this.enrollmentNumber = enrollmentNumber;
		this.gender = gender;
		this.mobile = mobile;
		this.casteCategory = casteCategory;
		this.email = email;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUmn() {
		return umn;
	}

	public void setUmn(String umn) {
		this.umn = umn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUfn() {
		return ufn;
	}

	public void setUfn(String ufn) {
		this.ufn = ufn;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Long getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(Long enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public CasteCategory getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(CasteCategory casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "StudentDetails [id=" + id + ", title=" + title + ", motherName=" + motherName + ", surname=" + surname
				+ ", umn=" + umn + ", firstName=" + firstName + ", fatherName=" + fatherName + ", middleName="
				+ middleName + ", ufn=" + ufn + ", uName=" + uName + ", enrollmentNumber=" + enrollmentNumber
				+ ", gender=" + gender + ", mobile=" + mobile + ", casteCategory=" + casteCategory + ", email=" + email
				+ ", remark=" + remark + "]";
	}
	
}
