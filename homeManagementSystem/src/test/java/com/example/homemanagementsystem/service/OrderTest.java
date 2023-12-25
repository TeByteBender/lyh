package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.PageBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testListOrder() {
        PageBean pageBean = orderService.userListOrder(1, 0, 5);

        System.out.println(pageBean);
    }
}
