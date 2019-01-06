package com.edureka.ms.training.orderservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.edureka.ms.training.orderservice.model.Order;
import com.edureka.ms.training.orderservice.model.model.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerE2ETest {
    @Autowired
	MockMvc mockMvc;
    
    @Test
    public void shouldAceptRequestForTheOrderCreation() throws Exception {
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
    	 String orderRequestJSON = new ObjectMapper().writeValueAsString(orderRequest);
    	 
    	 mockMvc.perform(post("/order")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .accept(MediaType.APPLICATION_JSON)
    			 .content(orderRequestJSON)
    			 )
    	 .andDo(print())
    	 .andExpect(status().isCreated())
    	 .andExpect(content().string("true"));
    	 
    	 
    	 mockMvc.perform(get("/order?id=1")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .accept(MediaType.APPLICATION_JSON)
    			 .content(orderRequestJSON)
    			 )
    	 .andDo(print())
    	 .andExpect(status().isOk())
    	 .andExpect(content().string(new ObjectMapper().writeValueAsString(order)));
     }
}
