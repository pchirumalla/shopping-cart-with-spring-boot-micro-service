package com.edureka.ms.training.orderservice.advice;

import com.edureka.ms.training.orderservice.repository.OrderRepository;
import com.edureka.ms.training.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderAdvice {

    @Autowired
    OrderRepository orderRepository;

    @ExceptionHandler(OrderService.ProductServiceNotReachableException.class)
    public  void  rollbackTransaction(){

    }
}
