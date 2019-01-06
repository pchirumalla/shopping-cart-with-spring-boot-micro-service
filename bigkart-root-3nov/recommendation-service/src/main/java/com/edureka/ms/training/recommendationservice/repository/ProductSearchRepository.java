package com.edureka.ms.training.recommendationservice.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.edureka.ms.training.recommendationservice.model.Product;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<Product, Integer> {
	List<Product> findByName(String name);
	
}
