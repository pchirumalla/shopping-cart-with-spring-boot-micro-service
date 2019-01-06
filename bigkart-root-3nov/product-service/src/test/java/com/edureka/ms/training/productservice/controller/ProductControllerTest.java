package com.edureka.ms.training.productservice.controller;

import com.edureka.ms.training.productservice.model.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 
* ProductController Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 10, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    MockMvc  mockMvc;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: saveProduct(@RequestBody Product product) 
* 
*/ 
@Test
public void testSaveProduct() throws Exception {
//TODO: Test goes here...
    ProductRequest product = ProductRequest.builder()
            .id(1)
            .name("Samsung 8")
            .description("Samsung 8, Amoled Screen Mobile Phone")
            .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String productAsJSON = objectMapper.writeValueAsString(product);


    mockMvc.perform(
            post("/product")
            .content(productAsJSON)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)

         )       .andExpect(status().isCreated())
            .andDo(print());

    mockMvc.perform(
            get("/product")
                    .param("id", "1")

    )       .andExpect(status().isOk())
            .andExpect(content().string("true"))
            .andDo(print());

} 

/** 
* 
* Method: isProductInInventory(@RequestParam int id) 
* 
*/ 
@Test
public void testIsProductInInventory() throws Exception { 
//TODO: Test goes here... 
} 


} 
