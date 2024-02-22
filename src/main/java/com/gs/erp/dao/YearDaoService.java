package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Year;

@Component
public class YearDaoService {
	private static List<Year> years = new ArrayList<>();
	private static int yearCount = 0;

	public List<Year> findAll(){
		return years;
	}
	
	public Year save(Year year) {
		year.setId(++yearCount);
		years.add(year);
		return year;
	}
	
	public Year findOne(int id) {
		Predicate<? super Year> predicate = year -> year.getId().equals(id);
		return years.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Year> predicate = year -> year.getId().equals(id);
		years.removeIf(predicate);
	}
}
