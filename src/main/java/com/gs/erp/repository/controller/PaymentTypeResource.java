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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Sort;

import com.gs.erp.exception.UserNotFoundException;
import com.gs.erp.jparepository.PaymentTypeRepository;
import com.gs.erp.models.PaymentType;

import jakarta.validation.Valid;

@RestController
public class PaymentTypeResource {
	private PaymentTypeRepository paymentTypeRepository;
	
	public PaymentTypeResource(PaymentTypeRepository paymentTypeRepository) {
		this.paymentTypeRepository = paymentTypeRepository;
	}

	@GetMapping("/paymenttype")
	public List<PaymentType> retrieveAllPayment() {
	    Sort sortBy = Sort.by(Sort.Direction.DESC, "id"); // Assuming createdAt is the field representing submission time
	    return paymentTypeRepository.findAll(sortBy);
	}
	
	@GetMapping("/paymenttype/{id}")
	public EntityModel<PaymentType> retrievePayment(@PathVariable int id){
		Optional<PaymentType> paymenttype = paymentTypeRepository.findById(id);	
		if (paymenttype.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		EntityModel<PaymentType> entityModel = EntityModel.of(paymenttype.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPayment());
		entityModel.add(link.withRel("all-payments"));
		return entityModel;
	}
	
	@DeleteMapping("/paymenttype/{id}")
	public void deletePayment(@PathVariable int id){
		paymentTypeRepository.deleteById(id);		
	}
	
	@PostMapping("/paymenttype")
	public ResponseEntity<PaymentType> createPayment(@Valid @RequestBody PaymentType paymenttype) {
		PaymentType savedType = paymentTypeRepository.save(paymenttype);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedType.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/paymenttype/{id}")
    public ResponseEntity<PaymentType> updatePayment(@PathVariable int id, @Valid @RequestBody PaymentType updatedPaymentType) {
        Optional<PaymentType> optionalPaymentType = paymentTypeRepository.findById(id);
        
        if (optionalPaymentType.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        PaymentType existingPaymentType = optionalPaymentType.get();
        existingPaymentType.setPaymentType(updatedPaymentType.getPaymentType()); // Assuming there's a setName method in PaymentType
        existingPaymentType.setDescription(updatedPaymentType.getDescription());
        existingPaymentType.setCheckIfRte(updatedPaymentType.getCheckIfRte());
        existingPaymentType.setCheckIfActive(updatedPaymentType.getCheckIfActive());
        
        PaymentType updatedType = paymentTypeRepository.save(existingPaymentType);
        return ResponseEntity.ok(updatedType);
    }
}
