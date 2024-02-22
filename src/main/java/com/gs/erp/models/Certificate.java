package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "acd_master_certificate")
public class Certificate {

	@Id
	@GeneratedValue
	private Integer id;
	private String certificateCode;
	private String certificateName;
	private Integer originalCertificate;
	private Integer xeroxCertificate;
	private Boolean active;
	private Boolean document;

	protected Certificate() {
		
	}
	
	public Certificate(Integer id, String certificateCode, String certificateName, Integer originalCertificate,
			Integer xeroxCertificate, Boolean active, Boolean document) {
		super();
		this.id = id;
		this.certificateCode = certificateCode;
		this.certificateName = certificateName;
		this.originalCertificate = originalCertificate;
		this.xeroxCertificate = xeroxCertificate;
		this.active = active;
		this.document = document;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public Integer getOriginalCertificate() {
		return originalCertificate;
	}

	public void setOriginalCertificate(Integer originalCertificate) {
		this.originalCertificate = originalCertificate;
	}

	public Integer getXeroxCertificate() {
		return xeroxCertificate;
	}

	public void setXeroxCertificate(Integer xeroxCertificate) {
		this.xeroxCertificate = xeroxCertificate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getDocument() {
		return document;
	}

	public void setDocument(Boolean document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", certificateCode=" + certificateCode + ", certificateName=" + certificateName
				+ ", originalCertificate=" + originalCertificate + ", xeroxCertificate=" + xeroxCertificate
				+ ", active=" + active + ", document=" + document + "]";
	}

}
