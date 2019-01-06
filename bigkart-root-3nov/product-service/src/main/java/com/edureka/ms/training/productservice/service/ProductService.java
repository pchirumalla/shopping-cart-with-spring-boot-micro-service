package com.edureka.ms.training.productservice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.ms.training.productservice.model.Product;
import com.edureka.ms.training.productservice.model.ProductRequest;
import com.edureka.ms.training.productservice.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public boolean save(ProductRequest productRequest){
        Product tobeSavedProduct = new ProductMapper().map(productRequest);
        Product saved = productRepository.save(tobeSavedProduct);
        return  saved != null;
    }

    public List<Product> getAllProductsByName(String name) {
    	List<Product> products=  productRepository.findByNameLikeIgnoringCase(name);
    	return products;
    }
    
    public boolean existsById(int id){
        return productRepository.existsById(id);
    }

    public List<Product> loadAll() {
    	List<Product> products=  productRepository.findAll();
    	return products;
    }
    
    private class ProductMapper{
        public Product map(ProductRequest productRequest){
            return Product.builder()
                    .id(productRequest.getId())
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .build();
       }
    }
}
