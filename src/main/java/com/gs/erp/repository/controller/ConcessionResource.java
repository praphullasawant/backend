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
import com.gs.erp.jparepository.ConcessionRepository;
import com.gs.erp.models.Concession;

import jakarta.validation.Valid;

@RestController
public class ConcessionResource {
	private ConcessionRepository concessionRepository;
	
	public ConcessionResource(ConcessionRepository concessionRepository) {
		this.concessionRepository = concessionRepository;
	}

	@GetMapping("/concession")
	public List<Concession> retrieveAllConcession(){
		return concessionRepository.findAll();
	}
	
	@GetMapping("/concession/{id}")
	public EntityModel<Concession> retrieveConcession(@PathVariable int id){
		Optional<Concession> concession = concessionRepository.findById(id);	
		if (concession.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}	
		EntityModel<Concession> entityModel = EntityModel.of(concession.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllConcession());
		entityModel.add(link.withRel("all-concessions"));
		return entityModel;
	}
	
	@DeleteMapping("/concession/{id}")
	public void deleteConcession(@PathVariable int id){
		concessionRepository.deleteById(id);		
	}
	
	@PostMapping("/concession")
	public ResponseEntity<Concession> createConcession(@Valid @RequestBody Concession concession) {
		Concession savedConcession = concessionRepository.save(concession);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedConcession.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
