package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Cashbook;

@Component
public class CashbookDaoService {
	private static List<Cashbook> cashbooks = new ArrayList<>();
	private static int cashbookCount = 0;

	public List<Cashbook> findAll(){
		return cashbooks;
	}
	
	public Cashbook save(Cashbook cashbook) {
		cashbook.setId(++cashbookCount);
		cashbooks.add(cashbook);
		return cashbook;
	}
	
	public Cashbook findOne(int id) {
		Predicate<? super Cashbook> predicate = cashbook -> cashbook.getId().equals(id);
		return cashbooks.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Cashbook> predicate = cashbook -> cashbook.getId().equals(id);
		cashbooks.removeIf(predicate);
	}
}
