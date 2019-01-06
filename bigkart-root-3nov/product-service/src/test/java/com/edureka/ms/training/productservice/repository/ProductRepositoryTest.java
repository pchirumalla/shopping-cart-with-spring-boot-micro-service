package com.edureka.ms.training.productservice.repository;

import com.edureka.ms.training.productservice.model.Product;
import org.junit.Test;

public class ProductRepositoryTest {
    @Test
    public void test(){
        ProductRepository productRepository = null;
        productRepository.save(Product.builder().id(1)
                .name("one")
                .description("one").build());
    }
}
