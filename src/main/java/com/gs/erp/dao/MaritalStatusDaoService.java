package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.MaritalStatus;

@Component
public class MaritalStatusDaoService {

	private static List<MaritalStatus> mstatus = new ArrayList<>();
	private static int mstatusCount = 0;
	
	
	public List<MaritalStatus> findAll(){
		return mstatus;
	}
	
	public MaritalStatus save(MaritalStatus status) {
		status.setId(++mstatusCount);
		mstatus.add(status);
		return status;
	}
	
	public MaritalStatus findOne(int id) {
		Predicate<? super MaritalStatus> predicate = status -> status.getId().equals(id);
		return mstatus.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super MaritalStatus> predicate = status -> status.getId().equals(id); 
		mstatus.removeIf(predicate);
	}
}
