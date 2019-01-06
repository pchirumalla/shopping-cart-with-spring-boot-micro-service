package com.edureka.ms.training.productservice.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.edureka.ms.training.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {//, QuerydslPredicateExecutor<Product> {
	 /* @Query("{'name' : { $regex: ?0 }")
	  List<Product> findProductByRegex(final String name); */
	
	 List<Product> findByNameLikeIgnoringCase(final String name); 
	  
}
