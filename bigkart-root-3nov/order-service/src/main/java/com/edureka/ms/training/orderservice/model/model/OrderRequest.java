package com.edureka.ms.training.orderservice.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    Integer id;
    int userId;
    String address;
    int quantity;
    ProductDetail productDetail;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class ProductDetail {
        int code;
        String name;
        String description;
        
    }
}
