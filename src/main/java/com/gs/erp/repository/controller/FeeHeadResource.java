package com.gs.erp.repository.controller;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.CashbookRepository;
import com.gs.erp.jparepository.FeeHeadRepository;
import com.gs.erp.models.Cashbook;
import com.gs.erp.models.FeeHead;

import jakarta.validation.Valid;

@RestController
public class FeeHeadResource {
	
	private FeeHeadRepository feeHeadRepository;
	private CashbookRepository cashBookRepository;
	
	public FeeHeadResource(FeeHeadRepository feeHeadRepository, CashbookRepository cashBookRepository) {
		this.feeHeadRepository = feeHeadRepository;
		this.cashBookRepository = cashBookRepository;
	}

	// retrieve all cashbook names 
	@GetMapping("/fhcashbook")
	public List<String> retrieveAllCashBook(){
		List<Cashbook> cashbooks = cashBookRepository.findAll();
		List<String> cashbookNames = new ArrayList<>();
		for(Cashbook book :cashbooks) {
			cashbookNames.add(book.getReceiptBookName());
		}
		return cashbookNames;
	}
	
	@GetMapping("/feehead")
	public List<FeeHead> retrieveAllFeeHead(){
		return feeHeadRepository.findAll();
	}
	
	@GetMapping("/feehead/{id}")
	public EntityModel<FeeHead> retrieveFeeHead(@PathVariable int id){
		Optional<FeeHead> feehead = feeHeadRepository.findById(id);
		if(feehead.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<FeeHead> entityModel = EntityModel.of(feehead.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllFeeHead());
		entityModel.add(link.withRel("all-feeheads"));
		return entityModel;
	}
	
	@DeleteMapping("feehead/{id}")
	public void deleteFeeHead(@PathVariable int id) {
		feeHeadRepository.deleteById(id);
	}
	
	@PostMapping("/feehead")
	public ResponseEntity<FeeHead> createFeeHead(@Valid @RequestBody FeeHead feehead){
		FeeHead savedFeeHead = feeHeadRepository.save(feehead);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedFeeHead.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
//	@PutMapping("/feehead/{id}")
//	public ResponseEntity<FeeHead> updateFeehead(@PathVariable int id, @Valid @RequestBody FeeHead feehead){
//		Optional<FeeHead> optionalFeehead = feeHeadRepository.findById(id);
//		if (optionalFeehead.isEmpty()) {
//            throw new UserNotFoundException("id:" + id);
//        }
//		FeeHead existingFeeType = optionalFeehead.get();
//		existingFeeType.setReceiptBook
//	} 
}
