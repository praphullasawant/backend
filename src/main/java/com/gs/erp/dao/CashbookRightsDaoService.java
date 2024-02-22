package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.CashbookRights;

@Component
public class CashbookRightsDaoService {
	private static List<CashbookRights> rights =new ArrayList<>();
	private static int usersCount = 0;
	
	static{
		rights.add(new CashbookRights(++usersCount, "Person1", "Counter7"));
		rights.add(new CashbookRights(++usersCount, "Person2", "Counter2"));
		rights.add(new CashbookRights(++usersCount, "Person3", "Counter5"));
	}
	
	public List<CashbookRights> findAll(){
		return rights;
	}
	
	public CashbookRights save(CashbookRights cashbookrights) {
		cashbookrights.setId(++usersCount);
		rights.add(cashbookrights);
		return cashbookrights;
	}
	
	public CashbookRights findOne(int id) {
		Predicate<? super CashbookRights> predicate = user -> user.getId().equals(id); 
		return rights.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super CashbookRights> predicate = user -> user.getId().equals(id); 
		rights.removeIf(predicate);
	}
	
}
