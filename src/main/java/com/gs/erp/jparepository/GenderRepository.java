package com.gs.erp.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.erp.models.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer>{

}
