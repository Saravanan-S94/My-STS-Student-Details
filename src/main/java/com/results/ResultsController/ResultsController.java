package com.results.ResultsController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.results.ResultsService.ResultsService;
import com.results.entity.Results;

@RestController
@RequestMapping(value = "/result")
public class ResultsController {
	
	@Autowired
	ResultsService rs;
	
	@PostMapping("/calculate1")
	public String calc() {
		return rs.calc();
	}
	
	@GetMapping("/getAll")
	public List<Results> getAll() {
		return rs.getAll(); 
	}
	@GetMapping("/getTopper")
	public Results getTopper() {
		return rs.getTopper();
	}
	
	@GetMapping("/getTop3Rangers")
	public List<Results> getTop3() {
		return rs.getTop3();
	}

	@GetMapping("/Range")
	public List<Results> getRange() {
		return rs.getRange();
	}
}