package com.edureka.ms.training.recommendationservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.ms.training.recommendationservice.model.Product;
import com.edureka.ms.training.recommendationservice.service.RecommendationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "search")
public class ProductSearchController {
	RecommendationService recommendationService;
	
	@GetMapping
	public ResponseEntity<List<Product>> searchByName(@RequestParam String name){
		return ResponseEntity.status(HttpStatus.OK).body(recommendationService.searchByName(name));
	}
	
	@GetMapping("/text")
	public List<Product> searchEverything(@RequestParam String text){
		return recommendationService.search(text);
	}
	
	

}
