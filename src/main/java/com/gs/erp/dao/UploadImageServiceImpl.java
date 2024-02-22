package com.gs.erp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gs.erp.jparepository.UploadImageRepository;
import com.gs.erp.models.UploadImage;

@Component
public class UploadImageServiceImpl implements UploadImageService{
	@Autowired
    private UploadImageRepository uploadImageRepository;

    @Override
    public UploadImage create(UploadImage uploadImage) {
        return uploadImageRepository.save(uploadImage);
    }

    @Override
    public List<UploadImage> viewAll() {
        return uploadImageRepository.findAll();
    }

    @Override
    public UploadImage viewById(long id) {
        return uploadImageRepository.findById(id).orElse(null);
    }
}
