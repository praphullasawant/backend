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
import com.gs.erp.jparepository.MaritalStatusRepository;
import com.gs.erp.models.MaritalStatus;

import jakarta.validation.Valid;

@RestController
public class MaritalStatusResource {
	private MaritalStatusRepository maritalStatusRepository;
	
	public MaritalStatusResource(MaritalStatusRepository maritalStatusRepository) {
		this.maritalStatusRepository = maritalStatusRepository;
	}

	@GetMapping("/maritalstatus")
	public List<MaritalStatus> retrieveAllStatus(){
		return maritalStatusRepository.findAll();
	}
	
	@GetMapping("/maritalstatus/{id}")
	public EntityModel<MaritalStatus> retrievestatus(@PathVariable int id){
		Optional<MaritalStatus> status = maritalStatusRepository.findById(id);	
		if (status.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<MaritalStatus> entityModel = EntityModel.of(status.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllStatus());
		entityModel.add(link.withRel("all-status"));
		
		return entityModel;
	}
	
	@DeleteMapping("/maritalstatus/{id}")
	public void deleteStatus(@PathVariable int id){
		maritalStatusRepository.deleteById(id);	
	}
	
	@PostMapping("/maritalstatus")
	public ResponseEntity<MaritalStatus> createStatus(@Valid @RequestBody MaritalStatus status) {
		MaritalStatus savedStatus = maritalStatusRepository.save(status);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStatus.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
