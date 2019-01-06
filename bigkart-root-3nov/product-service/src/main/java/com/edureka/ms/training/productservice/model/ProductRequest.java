package com.edureka.ms.training.productservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Document
public class ProductRequest {
    Integer id;

    String name;

    String description;
}
