package com.gs.erp.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_table")
public class UploadImage {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Lob
    private byte[] photoImageData;
    
    @Lob
    private byte[] photoSignData;
    
    private Date date; 
    
    public UploadImage() {}

	public UploadImage(long id, byte[] photoImageData, byte[] photoSignData, Date date) {
		super();
		this.id = id;
		this.photoImageData = photoImageData;
		this.photoSignData = photoSignData;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getPhotoImageData() {
		return photoImageData;
	}

	public void setPhotoImageData(byte[] photoImageData) {
		this.photoImageData = photoImageData;
	}

	public byte[] getPhotoSignData() {
		return photoSignData;
	}

	public void setPhotoSignData(byte[] photoSignData) {
		this.photoSignData = photoSignData;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}