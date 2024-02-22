package com.gs.erp.repository.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.dao.CashbookRightsDaoService;
import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.models.CashbookRights;

import jakarta.validation.Valid;

@RestController
public class CashbookRightsResource {
	private CashbookRightsDaoService service;
	
	public CashbookRightsResource(CashbookRightsDaoService service) {
		this.service=service;
	}
	
	@GetMapping("/cashbookrights")
	public List<CashbookRights> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/cashbookrights/{id}")
	public CashbookRights retrieveUser(@PathVariable int id){
		CashbookRights cashbookrights = service.findOne(id);	
		if (cashbookrights==null) {
			throw new UserNotFoundException("id:"+id);
		}	
		return cashbookrights;
	}
	
	@DeleteMapping("/cashbookrights/{id}")
	public void deleteRight(@PathVariable int id){
		service.deleteById(id);		
	}
	
	@PostMapping("/cashbookrights")
	public ResponseEntity<CashbookRights> createUser(@Valid @RequestBody CashbookRights cashbookrights) {
		CashbookRights savedRight = service.save(cashbookrights);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRight.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
