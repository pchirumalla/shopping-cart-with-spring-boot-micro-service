package com.edureka.ms.training.order.service.tdd.orderservicetdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.Order;
import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.model.OrderRequest;
import com.edureka.ms.training.order.service.tdd.orderservicetdd.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.POST)
	public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequest orderRequest){
		orderService.save(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(true);
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Order> getProduct(@RequestParam Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getProduct(id));
	}
	
	
	public void send(OrderRequest orderRequest) {
		//orderRequest.
	}
	
}
