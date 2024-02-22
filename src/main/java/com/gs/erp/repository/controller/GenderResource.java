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
import com.gs.erp.jparepository.GenderRepository;
import com.gs.erp.models.Gender;

import jakarta.validation.Valid;

@RestController
public class GenderResource {
	private GenderRepository genderRepository;

	public GenderResource(GenderRepository genderRepository) {
		this.genderRepository = genderRepository;
	}
	
	@GetMapping("/gender")
	public List<Gender> retrieveAllGender(){
		return genderRepository.findAll();
	}
	
	@GetMapping("/gender/{id}")
	public EntityModel<Gender> retrieveGender(@PathVariable int id){
		Optional<Gender> gender = genderRepository.findById(id);	
		if (gender.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<Gender> entityModel = EntityModel.of(gender.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllGender());
		entityModel.add(link.withRel("all-genders"));
		
		return entityModel;
	}
	
	@DeleteMapping("/gender/{id}")
	public void deleteGender(@PathVariable int id){
		genderRepository.deleteById(id);	
	}
	
	@PostMapping("/gender")
	public ResponseEntity<Gender> createGender(@Valid @RequestBody Gender gender) {
		Gender savedGender = genderRepository.save(gender);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedGender.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
