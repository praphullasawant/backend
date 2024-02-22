package com.gs.erp.repository.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.gs.erp.jparepository.BasicCourseRepository;
import com.gs.erp.jparepository.CourseCreationRepository;
import com.gs.erp.jparepository.FeePatternRepository;
import com.gs.erp.models.BasicCourse;
import com.gs.erp.models.CourseCreation;
import com.gs.erp.models.CoursePattern;
import com.gs.erp.models.FeePattern;

import jakarta.validation.Valid;

@RestController
public class CourseCreationResource {
	
	CourseCreationRepository courseCreationRepository;
	
	@Autowired
	BasicCourseRepository basicCourseRepository;
	FeePatternRepository feePatternRepository;

	public CourseCreationResource(CourseCreationRepository courseCreationRepository) {
		this.courseCreationRepository = courseCreationRepository;
	}

	@GetMapping("/coursecreation")
	public List<CourseCreation> retrieveAllCourse(){
		return courseCreationRepository.findAll();
	}
	
	@GetMapping("/coursecreation/{id}")
	public EntityModel<CourseCreation> retrieveCourse(@PathVariable int id) {
		Optional<CourseCreation> CourseCreation = courseCreationRepository.findById(id);
		if (CourseCreation.isEmpty()) {
			throw new UserNotFoundException("id:" + id);
		}
		EntityModel<CourseCreation> entityModel = EntityModel.of(CourseCreation.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllCourse());
		entityModel.add(link.withRel("all-details"));
		return entityModel;
	}
	
	@PostMapping("/coursecreation")
	public ResponseEntity<CourseCreation> createCourse(@Valid @RequestBody CourseCreation courseCreation) {
	    int duration = courseCreation.getDuration();
	    CoursePattern coursePattern = courseCreation.getCoursePattern();
	    FeePattern feePattern = courseCreation.getFeePattern();
	    boolean checkIfActive = courseCreation.getCheckIfActive();
	    int feeId = courseCreation.getFeePattern().getNoOfSemester();
	    
	    Optional<FeePattern> FeePattern = feePatternRepository.findById(feeId);
	    System.out.println("FeePattern : "+FeePattern);
	    
	    System.out.println("No. of Semester : "+courseCreation.getFeePattern().getNoOfSemester());
	    duration = duration * feeId; 
	    System.out.println(duration);
	    
	    for (int i = 1; i <= duration; i++) {
	        CourseCreation newCourse = new CourseCreation();
	        BasicCourse basicCourse = basicCourseRepository.findById(courseCreation.getBasicCourse().getId()).orElse(null);
	        
	        if (basicCourse == null) {
	            // Handle the case where the BasicCourse is not found
	            return ResponseEntity.badRequest().build();
	        }

	        newCourse.setBasicCourse(basicCourse);
	        newCourse.setDuration(courseCreation.getDuration());
	        newCourse.setCoursePattern(coursePattern);
	        newCourse.setFeePattern(feePattern);
	        newCourse.setCheckIfActive(checkIfActive);
	        newCourse.getBasicCourse().setBasicCourse(basicCourse.getBasicCourse() + "-" + i);
	        courseCreationRepository.save(newCourse);
	    }

	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(courseCreation.getId()).toUri();
	    return ResponseEntity.created(location).build();
	}


}
