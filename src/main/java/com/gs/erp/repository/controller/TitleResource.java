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
import com.gs.erp.jparepository.TitleRepository;
import com.gs.erp.models.Title;

import jakarta.validation.Valid;

@RestController
public class TitleResource {
	private TitleRepository titleRepository;

	public TitleResource(TitleRepository titleRepository) {
		this.titleRepository = titleRepository;
	}
	
	@GetMapping("/title")
	public List<Title> retrieveAllTitle(){
		return titleRepository.findAll();
	}
	
	@GetMapping("/title/{id}")
	public EntityModel<Title> retrieveTitle(@PathVariable int id){
		Optional<Title> title = titleRepository.findById(id);	
		if (title.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<Title> entityModel = EntityModel.of(title.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllTitle());
		entityModel.add(link.withRel("all-titles"));
		
		return entityModel;
	}
	
	@DeleteMapping("/title/{id}")
	public void deleteTitle(@PathVariable int id){
		titleRepository.deleteById(id);	
	}
	
	@PostMapping("/title")
	public ResponseEntity<Title> createUser(@Valid @RequestBody Title title) {
		Title savedTitle = titleRepository.save(title);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTitle.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
