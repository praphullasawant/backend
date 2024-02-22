package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.CasteCategory;

@Component
public class CasteCategoryDaoService {
	private static List<CasteCategory> castes = new ArrayList<>();
	private static int casteCount = 0;

	public List<CasteCategory> findAll(){
		return castes;
	}
	
	public CasteCategory save(CasteCategory casteCategory) {
		casteCategory.setId(++casteCount);
		castes.add(casteCategory);
		return casteCategory;
	}
	
	public CasteCategory findOne(int id) {
		Predicate<? super CasteCategory> predicate = casteCategory -> casteCategory.getId().equals(id);
		return castes.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super CasteCategory> predicate = casteCategory -> casteCategory.getId().equals(id);
		castes.removeIf(predicate);
	}
}
