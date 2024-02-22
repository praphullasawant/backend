package com.gs.erp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.gs.erp.models.FeeHead;

@Component
public class FeeHeadDaoService {
	private static List<FeeHead> feeHeads = new ArrayList<>();
	private static int feeHeadCount = 0;

	public List<FeeHead> findAll(){
		return feeHeads;
	}
	
	public FeeHead save(FeeHead feeHead) {
		feeHead.setId(++feeHeadCount);
		feeHeads.add(feeHead);
		return feeHead;
	}
	
	public FeeHead findOne(int id) {
		Predicate<? super FeeHead> predicate = feeHead -> feeHead.getId().equals(id);
		return feeHeads.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super FeeHead> predicate = feeHead -> feeHead.getId().equals(id);
		feeHeads.removeIf(predicate);
	}
}
