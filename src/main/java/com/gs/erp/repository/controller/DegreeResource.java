package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gs.erp.dao.DegreeDaoService;
import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.DegreeRepository;
import com.gs.erp.models.CoursePattern;
import com.gs.erp.models.Degree;

import jakarta.validation.Valid;

@RestController
public class DegreeResource {
	//private DegreeDaoService service;
	private DegreeRepository degreeRepository;
	
	public DegreeResource(DegreeRepository degreeRepository) {
		this.degreeRepository=degreeRepository;
	}
	
	@GetMapping("/degree")
	public List<Degree> retrieveAllDegree(){
		Sort sortDesc = Sort.by(Sort.Order.desc("id"));
		return degreeRepository.findAll(sortDesc);
	}
	

	
	@GetMapping("/degree/{id}")
	public EntityModel<Degree> retrieveDegree(@PathVariable int id) {
	    Optional<Degree> degree = degreeRepository.findById(id);

	    if (degree.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }

	    EntityModel<Degree> entityModel = EntityModel.of(degree.get());

	    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllDegree());
	    entityModel.add(link.withRel("all-degree"));

	    return entityModel;
	}
	
	@DeleteMapping("/degree/{id}")
	public void deleteDegree(@PathVariable int id){
		degreeRepository.deleteById(id);		
	}
	
	@PostMapping("/degree")
	public ResponseEntity<Degree> createDegree(@Valid @RequestBody Degree degree) {
		Degree savedDegree = degreeRepository.save(degree);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDegree.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/degree/{id}")
	public ResponseEntity<Degree> updateDegree(@PathVariable int id, @Valid @RequestBody Degree degree) {
	    Optional<Degree> existingDegree = degreeRepository.findById(id);
	    if (existingDegree.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }
	    existingDegree.get().setDegreeDiploma(degree.getDegreeDiploma()); 
	    existingDegree.get().setDegreeDiplomaName(degree.getDegreeDiplomaName()); 
	    existingDegree.get().setCheckIfActive(degree.getCheckIfActive());
	    Degree updatedDegree = degreeRepository.save(existingDegree.get());
	   // URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	    return ResponseEntity.ok().body(updatedDegree);
	}
}
