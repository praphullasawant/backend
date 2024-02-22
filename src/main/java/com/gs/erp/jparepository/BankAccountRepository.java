package com.gs.erp.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.erp.models.BankAcccount;


public interface BankAccountRepository extends JpaRepository<BankAcccount, Integer>{

}
