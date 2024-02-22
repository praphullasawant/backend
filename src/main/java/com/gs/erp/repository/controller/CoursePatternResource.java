package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


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

import com.gs.erp.dao.CoursePatternDaoService;
import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.CoursePatternRepository;
import com.gs.erp.models.CoursePattern;
import com.gs.erp.models.Handicap;
import com.gs.erp.models.PaymentType;

import jakarta.validation.Valid;

@RestController
public class CoursePatternResource {
	//private CoursePatternDaoService service;
	private CoursePatternRepository coursePatternRepository;
	
	public CoursePatternResource(CoursePatternRepository coursePatternRepository) {
		this.coursePatternRepository=coursePatternRepository;
	}
	
	@GetMapping("/coursepattern")
	public List<CoursePattern> retrieveAllPattern(){
		Sort sortByDescendingOrder = Sort.by(Sort.Order.desc("id"));
		return coursePatternRepository.findAll(sortByDescendingOrder);
		//return service.findAll();
	}
	
	
	@GetMapping("/coursepattern/{id}")
	public EntityModel<CoursePattern> retrieveCoursePattern(@PathVariable int id) {
	    Optional<CoursePattern> coursepattern = coursePatternRepository.findById(id);

	    if (coursepattern.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }

	    EntityModel<CoursePattern> entityModel = EntityModel.of(coursepattern.get());
	    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPattern());
	    entityModel.add(link.withRel("all-coursepattern"));

	    return entityModel;
	}

	
	@DeleteMapping("/coursepattern/{id}")
	public void deletePattern(@PathVariable int id){
		coursePatternRepository.deleteById(id);		
	}
	
	@PostMapping("/coursepattern")
	public ResponseEntity<CoursePattern> createPattern(@Valid @RequestBody CoursePattern coursepattern) {
		CoursePattern savedCoursePattern = coursePatternRepository.save(coursepattern);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCoursePattern.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/coursepattern/{id}")
	public ResponseEntity<CoursePattern> updatePaymentType(@PathVariable int id, @Valid @RequestBody CoursePattern coursepattern) {
	    Optional<CoursePattern> existingCoursePattern = coursePatternRepository.findById(id);
	    if (existingCoursePattern.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }
	    existingCoursePattern.get().setCoursePattern(coursepattern.getCoursePattern()); 
	    existingCoursePattern.get().setCoursePatternName(coursepattern.getCoursePatternName()); 
	    existingCoursePattern.get().setNoOfSemester(coursepattern.getNoOfSemester());
	    existingCoursePattern.get().setCheckIfActive(coursepattern.getCheckIfActive());
	    CoursePattern updatedCoursePattern = coursePatternRepository.save(existingCoursePattern.get());
	    //URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	    return ResponseEntity.ok().body(updatedCoursePattern);
	}
}
