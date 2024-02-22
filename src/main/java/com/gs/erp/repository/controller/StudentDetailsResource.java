package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.StudentDetailsRepository;
import com.gs.erp.models.StudentDetails;

import jakarta.validation.Valid;

@RestController
public class StudentDetailsResource {
	StudentDetailsRepository studentDetailsRepository;

	public StudentDetailsResource(StudentDetailsRepository studentDetailsRepository) {
		this.studentDetailsRepository = studentDetailsRepository;
	}
	
	@GetMapping("/studentdetails")
	public List<StudentDetails> retrieveAllDetails(){
		return studentDetailsRepository.findAll();
	}
	
	@GetMapping("/studentdetails/{id}")
	public EntityModel<StudentDetails> retrieveDetails(@PathVariable int id) {
		Optional<StudentDetails> studentdetails = studentDetailsRepository.findById(id);
		if (studentdetails.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		EntityModel<StudentDetails> entityModel = EntityModel.of(studentdetails.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllDetails());
		entityModel.add(link.withRel("all-details"));
		return entityModel;
	}
	
	@PostMapping("/studentdetails")
	public ResponseEntity<StudentDetails> createDetails(@Valid @RequestBody StudentDetails studentdetails) {
		StudentDetails savedDetails = studentDetailsRepository.save(studentdetails);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedDetails.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
