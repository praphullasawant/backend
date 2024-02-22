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
import com.gs.erp.jparepository.SectionRepository;
import com.gs.erp.models.Section;

import jakarta.validation.Valid;

@RestController
public class SectionResource {
	private SectionRepository sectionRepository;
	
	public SectionResource(SectionRepository sectionRepository) {
		super();
		this.sectionRepository = sectionRepository;
	}

	@GetMapping("/section")
	public List<Section> retrieveAllSection(){
		return sectionRepository.findAll();
	}
	
	@GetMapping("/section/{id}")
	public EntityModel<Section> retrieveSection(@PathVariable int id){
		Optional<Section> section = sectionRepository.findById(id);	
		if (section.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}	
		EntityModel<Section> entityModel = EntityModel.of(section.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllSection());
		entityModel.add(link.withRel("all-sessions"));
		return entityModel;
	}
	
	@DeleteMapping("/section/{id}")
	public void deleteSection(@PathVariable int id){
		sectionRepository.deleteById(id);		
	}
	
	@PostMapping("/section")
	public ResponseEntity<Section> createSection(@Valid @RequestBody Section section) {
		Section savedSection = sectionRepository.save(section);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSection.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
