package com.results.ResultsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.results.ResultsDAO.ResultsDAO;
import com.results.entity.MarkSheetPojo;
import com.results.entity.Results;
import com.results.entity.StudentPojo;

@Service
public class ResultsService {
	
	@Autowired
	ResultsDAO rd;
	
	@Autowired
	RestTemplate rt;
	
	public String calc() {
		
		String url1 = "http://localhost:8080/student/getAll";
		ResponseEntity<List<StudentPojo>> student = rt.exchange(url1,HttpMethod.GET,null,
				new ParameterizedTypeReference<List<StudentPojo>>() {});
		List<StudentPojo> students = student.getBody();
				
		String url2 = "http://localhost:8081/marksheet/getAll";
		ResponseEntity<List<MarkSheetPojo>> marksheet = rt.exchange(url2,HttpMethod.GET,null,
				new ParameterizedTypeReference <List<MarkSheetPojo>> () {});
		List<MarkSheetPojo> marksheets = marksheet.getBody();
		
		List<Results> results = new ArrayList<>();
		
		
	   for(StudentPojo student1 : students) {
		  for(MarkSheetPojo marksheet1 : marksheets) {
			  if(student1.getRollNumber() == marksheet1.getRollNumber()) {
				  
				  Results result = new Results();
				  
				  result.setRollNumber(student1.getRollNumber());
				  result.setName(student1.getName());
				  int totalMarks = (marksheet1.getSem1Total()+marksheet1.getSem2Total()) /2;
				  if(student1.getAttenance()>=90) {
					  totalMarks = totalMarks+5;
				  }
				  result.setTotalMarks(totalMarks);
				  result.setPercentage(totalMarks*100/100);
				  results.add(result);
			  }
		  }
	   }
	return rd.calc(results);	
}
	public List<Results> getAll() {
		return rd.getAll();
	}
	
	public Results getTopper() {
		
		List<Results> get = rd.getAll();
	    Results topper = get.stream().max(Comparator.comparing(Results::getTotalMarks)).get();
		return topper;
	}
	
	public List<Results> getTop3() {
		List<Results> all = rd.getAll();
		List<Results> top3 = all.stream()
				.sorted(Comparator.comparingInt(Results::getTotalMarks).reversed())
				.limit(3).toList();
		return top3;
	}
	public List<Results> getRange(){
		List<Results> range = rd.getAll().stream()
				.filter(x-> x.getTotalMarks()>70 && x.getTotalMarks()<90).toList();
		return range;
	}
}