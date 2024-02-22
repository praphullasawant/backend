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
import com.gs.erp.jparepository.BloodGroupRepository;
import com.gs.erp.models.BloodGroup;

import jakarta.validation.Valid;

@RestController
public class BloodGroupResource {
	private BloodGroupRepository bloodGroupRepository;
	
	public BloodGroupResource(BloodGroupRepository bloodGroupRepository) {
		this.bloodGroupRepository = bloodGroupRepository;
	}

	@GetMapping("/bloodGroup")
	public List<BloodGroup> retrieveAllBloodGroup(){
		return bloodGroupRepository.findAll();
	}
	
	@GetMapping("/bloodGroup/{id}")
	public EntityModel<BloodGroup> retrieveBloodgroup(@PathVariable int id){
		Optional<BloodGroup> bloodGroup = bloodGroupRepository.findById(id);	
		if (bloodGroup.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<BloodGroup> entityModel = EntityModel.of(bloodGroup.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllBloodGroup());
		entityModel.add(link.withRel("all-bloodGroups"));
		
		return entityModel;
	}
	
	@DeleteMapping("/bloodGroup/{id}")
	public void deleteTitle(@PathVariable int id){
		bloodGroupRepository.deleteById(id);	
	}
	
	@PostMapping("/bloodGroup")
	public ResponseEntity<BloodGroup> createBloodGroup(@Valid @RequestBody BloodGroup bloodGroup) {
		BloodGroup savedBloodGroup = bloodGroupRepository.save(bloodGroup);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBloodGroup.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
