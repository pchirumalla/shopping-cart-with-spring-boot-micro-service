package com.edureka.ms.training.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_order", catalog = "msdemand")
public class Order {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "user_id")
    int userId;
    @Column(name = "address")
    String address;
    @Column(name = "quantity")
    int quantity;
    @Embedded
    ProductDetail productDetail;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetail {
        @Column(name = "code")
        int code;
        @Column(name = "name")
        String name;
        @Column(name = "description")
        String description;
    }
}
