package com.edureka.ms.training.orderservice.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edureka.ms.training.orderservice.client.Product;
import com.edureka.ms.training.orderservice.client.ProductClient;
import com.edureka.ms.training.orderservice.client.RecommendationClient;
import com.edureka.ms.training.orderservice.model.Order;
import com.edureka.ms.training.orderservice.model.model.OrderRequest;
import com.edureka.ms.training.orderservice.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductClient productClient;
    
    @Autowired
    RecommendationClient recommendationClient;

    @Retryable(value = {ProductServiceNotReachableException.class}, maxAttempts = 2, backoff = @Backoff(delay=2000))
    public boolean createOrder(OrderRequest orderRequest) {
        Order orderToBeSaved = OrderTransformer.transform(orderRequest);
        ResponseEntity<Boolean> forEntity = null;
        try {
            forEntity = restTemplate.getForEntity("http://product-service/product?id=" + orderRequest.getProductDetail().getCode(), Boolean.class);
        }catch (Exception e){

            throw new ProductServiceNotReachableException();
        }
        Order saved = null;
        if(forEntity.getBody()){
            saved = orderRepository.save(orderToBeSaved);
        }
        //TODO - Contact Product service to see if the product exists.
        return saved != null;
    }
    public static class ProductServiceNotReachableException extends  RuntimeException{};
    public Order getOrder(int id) {
    	return orderRepository.findById(id).get();
    }
    public void deleteOrder(int id) {
    	orderRepository.deleteById(id);
	}
    public List<Product> searchAllProductsByName(String name){
    	ResponseEntity<List<com.edureka.ms.training.orderservice.client.Product>> response = null;
        try {
       	 response = productClient.searchProductsByNameContaining(name);
        }catch (Exception e){
       	 throw new RecommendationServiceNotReachableException();
        }
        List<com.edureka.ms.training.orderservice.client.Product> products = response.getBody();
        return products;
    }
    public List<com.edureka.ms.training.orderservice.client.Product> recommnedProductsByName(String name){
    	 ResponseEntity<List<com.edureka.ms.training.orderservice.client.Product>> response = null;
         try {
        	// response = recommendationClient.searchByName(name);
        	 response = recommendationClient.searchEverything(name);
         }catch (Exception e){
        	 throw new RecommendationServiceNotReachableException();
         }
         List<com.edureka.ms.training.orderservice.client.Product> products = response.getBody();
        // return products.collect(Collectors.toList());
         return products;
    }
    public static class RecommendationServiceNotReachableException extends  RuntimeException{
    	
    	
    };

    
    public boolean createOrderWithFeign(OrderRequest orderRequest) {
        Order orderToBeSaved = OrderTransformer.transform(orderRequest);
        ResponseEntity<Boolean> forEntity = productClient.isProductInventory(orderRequest.getProductDetail().getCode());
        Order saved = null;
        if(forEntity.getBody()){
            saved = orderRepository.save(orderToBeSaved);
        }
        //TODO - Contact Product service to see if the product exists.
        return saved != null;
    }
    private static class OrderTransformer {
        public static Order transform(OrderRequest orderRequest) {
            return Order.builder().id(orderRequest.getId())
                    .userId(orderRequest.getUserId())
                    .quantity(orderRequest.getQuantity())
                    .address(orderRequest.getAddress())
                    .productDetail(Order.ProductDetail.builder()
                            .code(orderRequest.getProductDetail().getCode())
                            .description(orderRequest.getProductDetail().getDescription())
                            .name(orderRequest.getProductDetail().getName()).build()).build();
        }
    }
   /* @Component
    @Getter
    class MyProductClient{
    	private ProductClient myProductClient;

    	    @Autowired
    	    public MyProductClient(ProductClient productClient) {
    	        this.myProductClient = productClient;
    	    }
    }
    @Component
    @Getter
    class MyRecommendationClient{
    	private RecommendationClient myRecommendationClient;

    	    @Autowired
    	    public MyRecommendationClient(RecommendationClient recommendationClient) {
    	        this.myRecommendationClient = recommendationClient;
    	    }

    }*/
    
}