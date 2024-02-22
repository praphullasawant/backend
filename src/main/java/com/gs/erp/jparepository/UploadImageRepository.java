package com.gs.erp.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gs.erp.models.UploadImage;

@Repository
public interface UploadImageRepository extends JpaRepository<UploadImage, Long>{

}
