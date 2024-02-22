package com.gs.erp.dao;

import java.util.List;

import com.gs.erp.models.UploadImage;

public interface UploadImageService {
	
	UploadImage create(UploadImage uploadImage);
    List<UploadImage> viewAll();
    UploadImage viewById(long id);

}
