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
import com.gs.erp.jparepository.CasteCategoryRepository;
import com.gs.erp.jparepository.SubCasteRepository;
import com.gs.erp.models.CasteCategory;
import com.gs.erp.models.SubCaste;

import jakarta.validation.Valid;

@RestController
public class CasteCategoryResource {
	
	private CasteCategoryRepository casteCategoryRepository;
	private SubCasteRepository subCasteRepository;
	
	public CasteCategoryResource(CasteCategoryRepository casteCategoryRepository,
			SubCasteRepository subCasteRepository) {
		this.casteCategoryRepository = casteCategoryRepository;
		this.subCasteRepository = subCasteRepository;
	}
	
	//This method will retrive all castecategories
	@GetMapping("/castecategory")
	public List<CasteCategory> retrieveAllCaste(){
		return casteCategoryRepository.findAll();
	}
	
	@GetMapping("/castecategory/{id}")
	public EntityModel<CasteCategory> retrieveCaste(@PathVariable int id){
		Optional<CasteCategory> casteCategory = casteCategoryRepository.findById(id);
		if(casteCategory.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<CasteCategory> entityModel = EntityModel.of(casteCategory.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllCaste());
		entityModel.add(link.withRel("all-castes"));
		return entityModel;
	}
	
	@DeleteMapping("/castecategory/{id}")
	public void deleteCaste(@PathVariable int id) {
		casteCategoryRepository.deleteById(id);
	}
	
	@PostMapping("/castecategory")
	public ResponseEntity<CasteCategory> createCaste(@Valid @RequestBody CasteCategory casteCategory){
		CasteCategory savedCaste = casteCategoryRepository.save(casteCategory);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCaste.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/castecategory/{id}/subcaste")
	public List<SubCaste> retriveSubCasteForCaste(@PathVariable int id){
		Optional<CasteCategory> casteCategory = casteCategoryRepository.findById(id);
		if(casteCategory.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		return casteCategory.get().getSubCaste();
	}
	
	@PostMapping("/castecategory/{id}/subcaste")
	public ResponseEntity<Object> createSubCasteForCaste(@PathVariable int id, @Valid @RequestBody SubCaste subCaste){
		Optional<CasteCategory> casteCategory = casteCategoryRepository.findById(id);
		if(casteCategory.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		subCaste.setCastecategory(casteCategory.get());
		SubCaste savedSubCaste = subCasteRepository.save(subCaste);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSubCaste.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
