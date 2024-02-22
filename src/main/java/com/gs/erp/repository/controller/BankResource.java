package com.gs.erp.repository.controller;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.BankAccountRepository;
import com.gs.erp.jparepository.BankRepository;
import com.gs.erp.models.Bank;
import com.gs.erp.models.BankAcccount;

import jakarta.validation.Valid;

@RestController
public class BankResource {
	
	private BankRepository bankRepository;
	private BankAccountRepository bankAccountRepository;
	
	public BankResource(BankRepository bankRepository, BankAccountRepository bankAccountRepository) {
		this.bankRepository = bankRepository;
		this.bankAccountRepository = bankAccountRepository;
	}

	@GetMapping("/bank")
	public List<Bank> retrieveAllBank(){
		return bankRepository.findAll();
	}
	
	@GetMapping("/bank/{id}")
	public EntityModel<Bank> retrieveBank(@PathVariable int id){
		Optional<Bank> bank = bankRepository.findById(id);
		if(bank.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<Bank> entityModel = EntityModel.of(bank.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllBank());
		entityModel.add(link.withRel("all-banks"));
		return entityModel;
	}
	
	@DeleteMapping("/bank/{id}")
	public void deleteBank(@PathVariable int id) {
		bankRepository.deleteById(id);
	}
	
	@PostMapping("/bank")
	public ResponseEntity<Bank> createBank(@Valid @RequestBody Bank bank){
		Bank savedBank = bankRepository.save(bank);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBank.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/bank/{id}/bankaccount")
	public List<BankAcccount> retriveBankAccountForBank(@PathVariable int id){
		Optional<Bank> bank = bankRepository.findById(id);
		if(bank.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		return bank.get().getBankAccount();
	}
	
	@PostMapping("/bank/{id}/bankaccount")
	public ResponseEntity<Object> createBankAccountforBank(@PathVariable int id, @Valid @RequestBody BankAcccount bankAcccount){
		Optional<Bank> bank = bankRepository.findById(id);
		if(bank.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		bankAcccount.setBank(bank.get());
		BankAcccount savedBankAccount = bankAccountRepository.save(bankAcccount);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBankAccount.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
