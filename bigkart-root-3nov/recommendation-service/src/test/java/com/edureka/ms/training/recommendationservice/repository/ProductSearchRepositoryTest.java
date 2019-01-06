package com.edureka.ms.training.recommendationservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;





@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductSearchRepositoryTest {
    @Autowired
    ProductSearchRepository productRepository;

    @Test
    public void dummyTest(){

    }

   /* @Test
    public void findAll(){
       Iterable<Product> products = productRepository.findAll();
       while(products.iterator().hasNext()) {
    	   System.out.println(products.iterator().next());
       }
    }*/
   


}
