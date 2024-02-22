package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Degree;

@Component
public class DegreeDaoService {
	
	private static List<Degree> degrees =new ArrayList<>();
	private static int degreesCount = 0;
	
	public List<Degree> findAll(){
		return degrees;
	}

	public Degree save(Degree degree) {
		degree.setId(++degreesCount);
		degrees.add(degree);
		return degree;
	}
	
	public Degree findOne(int id) {
		Predicate<? super Degree> predicate = user -> user.getId().equals(id); 
		return degrees.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Degree> predicate = user -> user.getId().equals(id); 
		degrees.removeIf(predicate);
	}
}
