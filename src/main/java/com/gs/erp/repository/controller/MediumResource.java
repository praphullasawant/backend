package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import com.gs.erp.jparepository.MediumRepository;
import com.gs.erp.models.Medium;

import jakarta.validation.Valid;

@RestController
public class MediumResource {
	private MediumRepository mediumRepository;
	
	public MediumResource(MediumRepository mediumRepository) {
		super();
		this.mediumRepository = mediumRepository;
	}

	@GetMapping("/medium")
	public List<Medium> retrieveAllMedium(){
		return mediumRepository.findAll();
	}
	
	@GetMapping("/medium/{id}")
	public EntityModel<Medium> retrieveMedium(@PathVariable int id){
		Optional<Medium> medium = mediumRepository.findById(id);	
		if (medium.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}	
		EntityModel<Medium> entityModel = EntityModel.of(medium.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllMedium());
		entityModel.add(link.withRel("all-mediums"));
		return entityModel;
	}
	
	@DeleteMapping("/medium/{id}")
	public void deleteMedium(@PathVariable int id){
		mediumRepository.deleteById(id);		
	}
	
	@PostMapping("/medium")
	public ResponseEntity<Medium> createMedium(@Valid @RequestBody Medium medium) {
		Medium savedMedium = mediumRepository.save(medium);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedMedium.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
