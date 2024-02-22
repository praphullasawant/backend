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
import com.gs.erp.jparepository.HandicapRepository;
import com.gs.erp.models.Handicap;

import jakarta.validation.Valid;

@RestController
public class HandicapResource {
	private HandicapRepository handicapRepository;
	
	public HandicapResource(HandicapRepository handicapRepository) {
		this.handicapRepository = handicapRepository;
	}

	@GetMapping("/handicap")
	public List<Handicap> retrieveAllHandicap(){
		return handicapRepository.findAll();
	}
	
	@GetMapping("/handicap/{id}")
	public EntityModel<Handicap> retrieveHandicap(@PathVariable int id){
		Optional<Handicap> handicap = handicapRepository.findById(id);	
		if (handicap.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<Handicap> entityModel = EntityModel.of(handicap.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllHandicap());
		entityModel.add(link.withRel("all-handicaps"));
		
		return entityModel;
	}
	
	@DeleteMapping("/handicap/{id}")
	public void deleteTitle(@PathVariable int id){
		handicapRepository.deleteById(id);	
	}
	
	@PostMapping("/handicap")
	public ResponseEntity<Handicap> createHandicap(@Valid @RequestBody Handicap handicap) {
		Handicap savedHandicap = handicapRepository.save(handicap);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedHandicap.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
