package com.edureka.ms.training.recommendationservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.edureka.ms.training.recommendationservice.model.Product;
import com.edureka.ms.training.recommendationservice.repository.ProductSearchRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@EnableBinding(Sink.class)
public class RecommendationService {
	
	ProductSearchRepository productSearchRepository;
	
	ElasticsearchTemplate elasticsearchTemplate;
	
    public List<Product> searchByName(String name){
    	return productSearchRepository.findByName(name);
    }
    
    public List<Product> search(String text){
    	/*NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
    	.withFilter(QueryBuilders.regexpQuery("_all", ".*" + text + ".*" ))
    	.build();
    	Stream<Product> productList=  elasticsearchTemplate.queryForList(nativeSearchQuery, Product.class).stream();
    	List<Product> products =  productList.collect(Collectors.toList());
    	return products;*/
    	
    	
    	SearchQuery searchQuery = new NativeSearchQueryBuilder()
    			  .withQuery(QueryBuilders.multiMatchQuery(text, "name", "description"))
    			  .build();
    	List<Product> productList = elasticsearchTemplate
    			  .queryForList(searchQuery, Product.class);
    	
    	return productList;
	}
    
    @StreamListener(Sink.INPUT)
    public void saveProductInfo(String productInfo) {
    	String[] productData = productInfo.split(":");
    	Product product = new Product(7, productData[1], productData[2], BigDecimal.valueOf(8.99));
    	productSearchRepository.save(product);
    	System.out.println("Product "+productData[1] + "is saved");
    	
    }
}
