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
import com.gs.erp.jparepository.NationalityRepository;
import com.gs.erp.models.Nationality;

import jakarta.validation.Valid;

@RestController
public class NationalityResource {
	private NationalityRepository nationalityRepository;
	
	public NationalityResource(NationalityRepository nationalityRepository) {
		this.nationalityRepository = nationalityRepository;
	}

	@GetMapping("/nationality")
	public List<Nationality> retrieveAllNationality(){
		return nationalityRepository.findAll();
	}
	
	@GetMapping("/nationality/{id}")
	public EntityModel<Nationality> retrieveNationality(@PathVariable int id){
		Optional<Nationality> nationality = nationalityRepository.findById(id);	
		if (nationality.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<Nationality> entityModel = EntityModel.of(nationality.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllNationality());
		entityModel.add(link.withRel("all-nationalitys"));
		
		return entityModel;
	}

	@DeleteMapping("/nationality/{id}")
	public void deleteNationality(@PathVariable int id){
		nationalityRepository.deleteById(id);	
	}
	
	@PostMapping("/nationality")
	public ResponseEntity<Nationality> createNationality(@Valid @RequestBody Nationality nationality) {
		Nationality savedNationality = nationalityRepository.save(nationality);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedNationality.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
