package com.edureka.ms.training.orderservice.client;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("recommendation-service")
@RibbonClient("recommendation-service")
public interface RecommendationClient {
	@GetMapping("/search")
    public ResponseEntity<List<Product>> searchByName(@RequestParam("name") String name);
	
	@GetMapping("/search/text")
    public ResponseEntity<List<Product>> searchEverything(@RequestParam("text") String text);
	
}
