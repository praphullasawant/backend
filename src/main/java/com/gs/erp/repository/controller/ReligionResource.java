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
import com.gs.erp.jparepository.ReligionRepository;
import com.gs.erp.models.Religion;

import jakarta.validation.Valid;

@RestController
public class ReligionResource {
	private ReligionRepository religionRepository;
	
	public ReligionResource(ReligionRepository religionRepository) {
		this.religionRepository = religionRepository;
	}

	@GetMapping("/religion")
	public List<Religion> retrieveAllReligion(){
		return religionRepository.findAll();
	}
	
	@GetMapping("/religion/{id}")
	public EntityModel<Religion> retrieveTitle(@PathVariable int id){
		Optional<Religion> religion = religionRepository.findById(id);	
		if (religion.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<Religion> entityModel = EntityModel.of(religion.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllReligion());
		entityModel.add(link.withRel("all-religions"));
		
		return entityModel;
	}

	@DeleteMapping("/religion/{id}")
	public void deleteTitle(@PathVariable int id){
		religionRepository.deleteById(id);	
	}
	
	@PostMapping("/religion")
	public ResponseEntity<Religion> createReligion(@Valid @RequestBody Religion religion) {
		Religion savedReligion = religionRepository.save(religion);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedReligion.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
