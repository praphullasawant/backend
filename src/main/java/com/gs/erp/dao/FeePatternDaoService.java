package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.FeePattern;

@Component
public class FeePatternDaoService {
	
	private static List<FeePattern> feePatterns =new ArrayList<>();
	private static int feePatternCount = 0;
	
	public List<FeePattern> findAll(){
		return feePatterns;
	}

	public FeePattern save(FeePattern pattern) {
		pattern.setId(++feePatternCount);
		feePatterns.add(pattern);
		return pattern;
	}
	
	public FeePattern findOne(int id) {
		Predicate<? super FeePattern> predicate = faculty -> faculty.getId().equals(id); 
		return feePatterns.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super FeePattern> predicate = faculty -> faculty.getId().equals(id); 
		feePatterns.removeIf(predicate);
	}
}
