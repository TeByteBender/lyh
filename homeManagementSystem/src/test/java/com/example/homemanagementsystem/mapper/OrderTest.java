package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderMapper orderMapper;

//    @Test
//    public void testListOrder() {
//        List<Order> orders = orderMapper.getAllUserOrder(1, 0, 5);
//
//        for (Order order : orders) {
//            System.out.println(order);
//        }
//    }

}
