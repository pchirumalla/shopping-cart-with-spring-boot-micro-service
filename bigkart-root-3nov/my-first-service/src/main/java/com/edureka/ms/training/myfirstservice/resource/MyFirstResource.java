package com.edureka.ms.training.myfirstservice.resource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
public class MyFirstResource {
    @GetMapping
    public String helloEdureka(){
        return  "Hello Edureka";
    }
}
