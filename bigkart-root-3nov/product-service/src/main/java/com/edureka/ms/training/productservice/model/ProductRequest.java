package com.edureka.ms.training.productservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Document
public class ProductRequest {
    Integer id;

    String name;

    String description;
}
