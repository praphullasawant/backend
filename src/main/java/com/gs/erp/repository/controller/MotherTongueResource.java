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
import com.gs.erp.jparepository.MotherTongueRepository;
import com.gs.erp.models.MotherTongue;

import jakarta.validation.Valid;

@RestController
public class MotherTongueResource {
	private MotherTongueRepository motherTongueRepository;
	
	public MotherTongueResource(MotherTongueRepository motherTongueRepository) {
		this.motherTongueRepository = motherTongueRepository;
	}

	@GetMapping("/mothertongue")
	public List<MotherTongue> retrieveAllMotherTongue(){
		return motherTongueRepository.findAll();
	}
	
	@GetMapping("/mothertongue/{id}")
	public EntityModel<MotherTongue> retrieveMotherTongue(@PathVariable int id){
		Optional<MotherTongue> mothertongue = motherTongueRepository.findById(id);	
		if (mothertongue.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<MotherTongue> entityModel = EntityModel.of(mothertongue.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllMotherTongue());
		entityModel.add(link.withRel("all-mothertongues"));
		
		return entityModel;
	}

	@DeleteMapping("/mothertongue/{id}")
	public void deleteMotherTongue(@PathVariable int id){
		motherTongueRepository.deleteById(id);	
	}
	
	@PostMapping("/mothertongue")
	public ResponseEntity<MotherTongue> createMotherTongue(@Valid @RequestBody MotherTongue mothertongue) {
		MotherTongue savedMotherTongue = motherTongueRepository.save(mothertongue);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedMotherTongue.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
