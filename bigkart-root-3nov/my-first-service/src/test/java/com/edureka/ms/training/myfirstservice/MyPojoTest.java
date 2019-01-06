package com.edureka.ms.training.myfirstservice;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MyPojoTest {
    @Test
    public void shouldGetAndSetForAPojo(){
        MyPojo myPojo = new MyPojo();
        myPojo.setAge(10);
        Assertions.assertThat(myPojo.getAge()).isEqualTo(10);

    }
    @Test
    public void shouldTestBuilder(){
       MyPojo.builder().address("address")
               .age(10).name("Eureka").build();
    }
}
