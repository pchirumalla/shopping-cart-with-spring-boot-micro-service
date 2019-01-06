package com.edureka.ms.training.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.ms.training.productservice.model.Product;
import com.edureka.ms.training.productservice.model.ProductRequest;
import com.edureka.ms.training.productservice.service.ProductService;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveProduct(@RequestBody ProductRequest product) {
        boolean saved = productService.save(product);
        if (saved) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new ProductNotCreatedException();
        }
    }

    @GetMapping
    public ResponseEntity<Boolean> isProductInventory(@RequestParam int id) {
    	boolean productExists = productService.existsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productExists);
    }
    
    

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
    	List<Product> products = productService.loadAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
    
    
    /*@GetMapping("/byName")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
    	List<Product> products = productService.getAllProductsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }*/
    
    @GetMapping("/byName/containing")
    public List<Product> searchProductsByNameContaining(@RequestParam String name) {
        return productService.getAllProductsByName(name);
    }

    private static class ProductNotCreatedException extends RuntimeException {

    }

}
