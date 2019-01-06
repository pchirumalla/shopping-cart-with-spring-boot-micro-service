package com.edureka.ms.training.order.service.tdd.orderservicetdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.Order;
import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.model.OrderRequest;
import com.edureka.ms.training.order.service.tdd.orderservicetdd.repository.OrderRepository;
@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public boolean save(OrderRequest orderRequest) {
		Order order = Order.builder()
						.id(orderRequest.getId())
						.userId(orderRequest.getUserId())
						.productDetail(Order.ProductDetail.builder()
								.code(orderRequest.getProductDetail().getCode())
								.description(orderRequest.getProductDetail().getDescription())
								.name(orderRequest.getProductDetail().getName())
								.build())
						.quantity(orderRequest.getQuantity())
						.address(orderRequest.getAddress())
						.build();
		Order savedOrder = orderRepository.save(order);
		return savedOrder != null;
	}
	
	public Order getProduct(Integer id) {
		return orderRepository.findById(id).get();
	}
}
