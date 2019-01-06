package com.edureka.ms.training.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.ms.training.orderservice.client.Product;
import com.edureka.ms.training.orderservice.model.Order;
import com.edureka.ms.training.orderservice.model.model.OrderRequest;
import com.edureka.ms.training.orderservice.model.model.OrderRequest.ProductDetail;
import com.edureka.ms.training.orderservice.service.OrderService;

import lombok.Getter;

@RestController
@EnableBinding(Source.class)
@EnableAutoConfiguration
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    OrderService orderService;
    
    @Autowired
    private MySource mySource;
    
   /* @PostMapping
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequest orderRequest) {
        if (orderService.createOrder(orderRequest)) {
        	  send(orderRequest);
        	  return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new OrderNotCreatedException();
        }
    }*/
    
    @PostMapping
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<com.edureka.ms.training.orderservice.client.Product> createOrder(@RequestBody OrderRequest orderRequest) {
        if (orderService.createOrderWithFeign(orderRequest)) {
        	send(orderRequest);
        	return orderService.recommnedProductsByName(orderRequest.getProductDetail().getName());
        } else {
            throw new OrderNotCreatedException();
        }
    }

    private class OrderNotCreatedException extends RuntimeException {
    }
    @GetMapping
    public List<Product> getAllProductsByName(@RequestParam String name){
    	return orderService.searchAllProductsByName(name);
    }
    @GetMapping("/get")
    public Order getOrder(@RequestParam int id) {
        return orderService.getOrder(id);
    }
    
    @DeleteMapping
    public void deleteOrder(@RequestParam int id) {
        orderService.deleteOrder(id);
    }
    public void send(OrderRequest orderRequest) {
    	ProductDetail productDetail = orderRequest.getProductDetail();
    	StringBuffer sb = new StringBuffer().append(productDetail.getCode());
    	sb.append(":").append(productDetail.getName()).append(":").append(productDetail.getDescription());
    	mySource.getSource().output().send(MessageBuilder.withPayload(sb.toString()).build());
    	System.out.println("Product "+productDetail.getName()+ " is sent");
    }
    @Component
    @Getter
    class MySource{
    	private Source source;

    	    @Autowired
    	    public MySource(Source source) {
    	        this.source = source;
    	    }

    }
      
      
}
