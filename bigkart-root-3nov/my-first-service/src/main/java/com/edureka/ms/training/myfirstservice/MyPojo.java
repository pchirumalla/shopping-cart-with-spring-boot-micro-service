package com.edureka.ms.training.myfirstservice;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MyPojo {
    private String name;
    private int age;
    private String address;

}