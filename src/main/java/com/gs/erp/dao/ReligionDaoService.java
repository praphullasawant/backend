package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Religion;

@Component
public class ReligionDaoService {

	private static List<Religion> religions = new ArrayList<>();
	private static int religionCount = 0;
	
	
	public List<Religion> findAll(){
		return religions;
	}
	
	public Religion save(Religion religion) {
		religion.setId(++religionCount);
		religions.add(religion);
		return religion;
	}
	
	public Religion findOne(int id) {
		Predicate<? super Religion> predicate = religion -> religion.getId().equals(id);
		return religions.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Religion> predicate = religion -> religion.getId().equals(id); 
		religions.removeIf(predicate);
	}
}
