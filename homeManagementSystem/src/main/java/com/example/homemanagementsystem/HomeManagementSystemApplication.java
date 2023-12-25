package com.example.homemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan("com.example.homemanagementsystem.filter")   // 开启对 servlet 组件的支持
@ServletComponentScan   // 开启对 servlet 组件的支持
@SpringBootApplication
public class HomeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeManagementSystemApplication.class, args);
    }

}
