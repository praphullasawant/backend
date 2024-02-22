package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.MotherTongue;

@Component
public class MotherTongueDaoService {

	private static List<MotherTongue> motherTongues = new ArrayList<>();
	private static int motherTongueCount = 0;
	
	
	public List<MotherTongue> findAll(){
		return motherTongues;
	}
	
	public MotherTongue save(MotherTongue motherTongue) {
		motherTongue.setId(++motherTongueCount);
		motherTongues.add(motherTongue);
		return motherTongue;
	}
	
	public MotherTongue findOne(int id) {
		Predicate<? super MotherTongue> predicate = motherTongue -> motherTongue.getId().equals(id);
		return motherTongues.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super MotherTongue> predicate = motherTongue -> motherTongue.getId().equals(id); 
		motherTongues.removeIf(predicate);
	}
}
