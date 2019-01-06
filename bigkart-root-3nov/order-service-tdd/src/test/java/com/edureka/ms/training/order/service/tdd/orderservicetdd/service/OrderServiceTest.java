package com.edureka.ms.training.order.service.tdd.orderservicetdd.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.Order;
import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.model.OrderRequest;
import com.edureka.ms.training.order.service.tdd.orderservicetdd.repository.OrderRepository;

public class OrderServiceTest {
	
	OrderService orderService;
	
	@Before
	public void setup(){
		orderService = new OrderService();
		orderService.orderRepository = Mockito.mock(OrderRepository.class);
	}
	
	@Test
	public void shouldEnsureInteractionWithRepositoryWhenSaved() {
       OrderRequest orderRequest = OrderRequest.builder()
    		   .address("Sacramento")
    		   .id(1)
    		   .productDetail(OrderRequest.ProductDetail.builder()
    				  .code(2)
    				  .description("Flat Iron hair straightner")
    				  .name("Flat Iron")
    				  .build())
    		   .quantity(1)
    		   .userId(2)
    		   .build();
       Order order = Order.builder()
    		   .userId(orderRequest.getUserId())
    		   .productDetail(Order.ProductDetail.builder()
    				   .code(orderRequest.getProductDetail().getCode())
    				   .description(orderRequest.getProductDetail().getDescription())
    				   .name(orderRequest.getProductDetail().getName())
    				   .build())
    		   .quantity(orderRequest.getQuantity())
    		   .address(orderRequest.getAddress())
    		   .build();
    		   		   
    		   
       
       Mockito.when(orderService.orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
       boolean isSaved = orderService.save(orderRequest);
       Assertions.assertThat(isSaved).isTrue();
       Mockito.verify(orderService.orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
       Mockito.verify(orderService.orderRepository, Mockito.never()).findAll();
	}
}
