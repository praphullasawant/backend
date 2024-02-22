package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.Title;

@Component
public class TitleDaoService {

	private static List<Title> titles = new ArrayList<>();
	private static int titleCount = 0;
	
	
	public List<Title> findAll(){
		return titles;
	}
	
	public Title save(Title title) {
		title.setId(++titleCount);
		titles.add(title);
		return title;
	}
	
	public Title findOne(int id) {
		Predicate<? super Title> predicate = title -> title.getId().equals(id);
		return titles.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super Title> predicate = title -> title.getId().equals(id); 
		titles.removeIf(predicate);
	}
}
