package com.edureka.ms.training.order.service.tdd.orderservicetdd.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edureka.ms.training.order.service.tdd.orderservicetdd.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderRepositoryTest {
    //@SUT
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void dummyTest(){

    }

    @Test
    //should-Then-Given-When(Optional)
    //public void should_SaveAProduct_GivenAValidOrder()
    public void should_SaveAProduct_GivenAValidOrder(){
      //  Given
        Order order = Order.builder()
                .address("Sacramento")
                .id(1)
                .productDetail(Order.ProductDetail.builder()
                        .code(2)
                        .description("")
                        .name("")
                        .build()
                )
                .quantity(1)
                .userId(2)
                .build();
        //When
        orderRepository.save(order);
        //Then
        Optional<Order> byId = orderRepository.findById(1);
        Assertions.assertThat(byId.isPresent());
        Assertions.assertThat(orderRepository.count()).isEqualTo(1);
        Assertions.assertThat(byId.get()).isEqualTo(order);

    }
    @Test
    //should-Then-Given-When(Optional)
    //public void should_SaveAProduct_GivenAInvalidValidOrder()
    public void should_SaveAProduct_GivenAnInvalidValidOrder(){
        //  Given
        Order order = Order.builder()
                .address("Sacramento")
                .id(2)
                .productDetail(Order.ProductDetail.builder()
                        .code(2)
                        .description("")
                        .name("")
                        .build()
                )
                .quantity(1)
                .build();
        //When
        try{
            orderRepository.save(order);
            Assertions.assertThat(false);
        }catch (Exception e){
            Assertions.assertThat(true);
        }

        //Then
       //Assertions.assertThat(orderRepository.findById(2)).isNull();
    }
   // @Ignore
    @Test
    public void shouldReturnFewOrdersinRange(){
        Order order1 = createOrder(1,2,2);
        Order order2 = createOrder(2,3,3);
        Order order3 = createOrder(3,4,4);
        Order order4 = createOrder(4,5,5);
        orderRepository.saveAll(Arrays.asList(order1,order2, order3, order4));
        List<Order> orderList = orderRepository.findOrderByQuantityBetween(3,4);
        Assertions.assertThat(orderList.size()==4);
   }
  private Order createOrder(Integer id, Integer quantity, Integer code){
      Order order = Order.builder()
              .address("Sacramento")
              .id(id)
              .productDetail(Order.ProductDetail.builder()
                      .code(2)
                      .description("")
                      .name("")
                      .build()
              )
              .quantity(quantity)
              .userId(code)
              .build();
        return order;
  }


}
