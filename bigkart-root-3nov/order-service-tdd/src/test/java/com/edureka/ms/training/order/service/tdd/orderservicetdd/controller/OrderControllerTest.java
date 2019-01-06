package com.edureka.ms.training.order.service.tdd.orderservicetdd.controller;



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

import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.model.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
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
    	 String orderRequestJSON = new ObjectMapper().writeValueAsString(orderRequest);
    	 mockMvc.perform(post("/product")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .accept(MediaType.APPLICATION_JSON)
    			 .content(orderRequestJSON)
    			 )
    	 .andDo(print())
    	 .andExpect(status().isCreated())
    	 .andExpect(content().string("true"));
     }
}
