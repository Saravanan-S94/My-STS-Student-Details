package com.results.ResultsRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.results.entity.Results;

public interface ResultsRepository extends JpaRepository<Results, Integer> {

}
