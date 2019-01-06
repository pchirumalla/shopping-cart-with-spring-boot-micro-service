package com.edureka.ms.training.order.service.tdd.orderservicetdd.repository;

import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

   List<Order> findOrderByQuantityBetween(Integer from, Integer to);
   
}