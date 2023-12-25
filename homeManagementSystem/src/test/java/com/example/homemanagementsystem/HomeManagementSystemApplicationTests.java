package com.example.homemanagementsystem;

import com.example.homemanagementsystem.mapper.ConsumerUserMapper;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeManagementSystemApplicationTests {

    @Autowired
    private ConsumerUserMapper consumerUserMapper;


    @Test
    void contextLoads() {
    }

}
