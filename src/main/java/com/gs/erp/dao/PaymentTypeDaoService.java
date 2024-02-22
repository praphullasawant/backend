package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.PaymentType;

@Component
public class PaymentTypeDaoService {
	
	private static List<PaymentType> payments =new ArrayList<>();
	private static int paymentsCount = 0;
	
	public List<PaymentType> findAll(){
		return payments;
	}

	public PaymentType save(PaymentType paymenttype) {
		paymenttype.setId(++paymentsCount);
		payments.add(paymenttype);
		return paymenttype;
	}
	
	public PaymentType findOne(int id) {
		Predicate<? super PaymentType> predicate = payment -> payment.getId().equals(id); 
		return payments.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super PaymentType> predicate = payment -> payment.getId().equals(id); 
		payments.removeIf(predicate);
	}
}
