package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer>{

	List<Quote> findByUserName(String userName);

}
