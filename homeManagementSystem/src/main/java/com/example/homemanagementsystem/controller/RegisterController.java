package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;
import com.example.homemanagementsystem.service.ConsumerUserService;
import com.example.homemanagementsystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private ConsumerUserService consumerUserService;

    @Autowired
    private WorkerService workerService;

    /**
     * 用户注册
     * @param consumerUser 用户注册
     * @return Result
     */
    @PostMapping("/consumerUserRegister")
    public Result consumerUserRegister(@RequestBody ConsumerUser consumerUser) {

        return consumerUserService.register(consumerUser);
    }

    /**
     * 家政人员注册
     * @param worker 家政人员
     * @return Result
     */
    @PostMapping("/workerRegister")
    public Result workerRegister(@RequestBody Worker worker) {
        return workerService.register(worker);
    }
}
