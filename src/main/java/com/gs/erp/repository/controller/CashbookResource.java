package com.gs.erp.repository.controller;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.BankRepository;
import com.gs.erp.jparepository.CashbookRepository;
import com.gs.erp.models.AcademicSession;
import com.gs.erp.models.Bank;
import com.gs.erp.models.BankAcccount;
import com.gs.erp.models.Cashbook;

import jakarta.validation.Valid;

@RestController
public class CashbookResource {
	
	private CashbookRepository cashbookRepository;
	private BankRepository bankRepository;
	
	public CashbookResource(CashbookRepository cashbookRepository ,BankRepository bankRepository) {
		this.cashbookRepository = cashbookRepository;
		this.bankRepository = bankRepository;
	}
	
	// retrieve all banknames
	@GetMapping("/cbbank")
	public List<String> retrieveAllBank(){
		List<Bank> banks = bankRepository.findAll();
		List<String> bankNames = new ArrayList<>();
		for(Bank bank:banks) {
			bankNames.add(bank.getBankName());
		}
		return bankNames;
	}
	
	// retrieve all bank account of specefic bank account
	@GetMapping("/cbbank/{id}/bankaccount")
	public List<String> retrieveBankAccountNumbersForBank(@PathVariable int id) {
	    Optional<Bank> bank = bankRepository.findById(id);
	    if (bank.isEmpty()) {
	        throw new UserNotFoundException("id : " + id);
	    }

	    List<BankAcccount> bankAccounts = bank.get().getBankAccount();
	    List<String> accountNumbers = new ArrayList<>();

	    for (BankAcccount account : bankAccounts) {
	        accountNumbers.add(account.getAccountNumber());
	    }

	    return accountNumbers;
	}
	
	@GetMapping("/cashbook")
	public List<Cashbook> retrieveAllCashbook(){
		Sort sortDesc = Sort.by(Sort.Order.desc("id"));
		return cashbookRepository.findAll(sortDesc);
	}
	
	@GetMapping("/cashbook/{id}")
	public EntityModel<Cashbook> retrieveCashbook(@PathVariable int id){
		Optional<Cashbook> cashbook = cashbookRepository.findById(id);
		if(cashbook.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<Cashbook> entityModel = EntityModel.of(cashbook.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllCashbook());
		entityModel.add(link.withRel("all-cashbooks"));
		return entityModel;
	}
	
	@DeleteMapping("cashbook/{id}")
	public void deleteCashbook(@PathVariable int id) {
		cashbookRepository.deleteById(id);
	}
	
	@PostMapping("/cashbook")
	public ResponseEntity<Cashbook> createCashbook(@Valid @RequestBody Cashbook cashbook){
		Cashbook savedCashbook = cashbookRepository.save(cashbook);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCashbook.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/cashbook/{id}")
	public ResponseEntity<Cashbook> updateCashbook(@PathVariable int id, @Valid @RequestBody Cashbook cashbook) {
	    Optional<Cashbook> existingCashbook = cashbookRepository.findById(id);
	    if (existingCashbook.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }
	    existingCashbook.get().setReceiptBookCode(cashbook.getReceiptBookCode());
	    existingCashbook.get().setReceiptBookName(cashbook.getReceiptBookName()); 
	    existingCashbook.get().setCashReceiptNo(cashbook.getCashReceiptNo());
	    existingCashbook.get().setBankReceiptNo(cashbook.getBankReceiptNo());
	    existingCashbook.get().setDocumentReceiptNo(cashbook.getDocumentReceiptNo());
	    existingCashbook.get().setHsnsacNo(cashbook.getHsnsacNo());
	    existingCashbook.get().setReceiptBookType(cashbook.getReceiptBookType());
	    existingCashbook.get().setSrNo(cashbook.getSrNo());
	    existingCashbook.get().setCheckIfGstApplicable(cashbook.getCheckIfGstApplicable());
	    existingCashbook.get().setCheckIfActive(cashbook.getCheckIfActive());
	    Cashbook updatedCashbook = cashbookRepository.save(existingCashbook.get());
	    return ResponseEntity.ok().body(updatedCashbook);
	}
	
	
}
