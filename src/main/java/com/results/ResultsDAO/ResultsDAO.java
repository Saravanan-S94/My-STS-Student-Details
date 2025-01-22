package com.results.ResultsDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.results.ResultsRepository.ResultsRepository;
import com.results.entity.Results;

@Repository
public class ResultsDAO {
	
	@Autowired
	ResultsRepository rr;
	
	public String calc(List<Results> res) {	
		rr.saveAll(res);
		return "Saved Successfully";
	}
public List<Results> getAll() {
	return rr.findAll();
}
	public List<Results> getTopper() {
		return rr.findAll();
	}
}
