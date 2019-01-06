package com.edureka.ms.training.recommendationservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Document(indexName="products", type="product", shards=1, replicas=0, refreshInterval="-1")
public class Product {
	@Id
    Integer id;
    String name;
    String description;
    BigDecimal price;
}
