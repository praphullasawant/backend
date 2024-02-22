package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Bank;

@Component
public class BankDaoService {
	private static List<Bank> banks = new ArrayList<>();
	private static int bankCount = 0;

	public List<Bank> findAll(){
		return banks;
	}
	
	public Bank save(Bank bank) {
		bank.setId(++bankCount);
		banks.add(bank);
		return bank;
	}
	
	public Bank findOne(int id) {
		Predicate<? super Bank> predicate = bank -> bank.getId().equals(id);
		return banks.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Bank> predicate = bank -> bank.getId().equals(id);
		banks.removeIf(predicate);
	}
}
