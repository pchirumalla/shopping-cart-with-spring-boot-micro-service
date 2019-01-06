package com.edureka.ms.training.orderservice.client;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-service")
@RibbonClient("product-service")
public interface ProductClient {
    @GetMapping("/product")
    public ResponseEntity<Boolean> isProductInventory(@RequestParam("id") int id);
    
    @GetMapping("/product/byName/containing")
    public ResponseEntity<List<com.edureka.ms.training.orderservice.client.Product>> searchProductsByNameContaining(@RequestParam("name") String name);
}
