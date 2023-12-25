package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;
import com.example.homemanagementsystem.service.AdminUserService;
import com.example.homemanagementsystem.service.ConsumerUserService;
import com.example.homemanagementsystem.service.WorkerService;
import com.example.homemanagementsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public ConsumerUserService consumerUserService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private WorkerService workerService;

    /**
     * 消费者登录
     * @param consumerUser 消费者对象
     * @return 结果
     */
    @PostMapping("/consumerUserLogin")
    public Result consumerUserLogin(@RequestBody ConsumerUser consumerUser) {
        ConsumerUser user = consumerUserService.login(consumerUser);

        // 登录成功，生成令牌，下发令牌
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();

            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("identity", 1);  // 身份：1，消费者；2，家政人员；3，管理员

            String jwt = JwtUtils.generateJwt(claims);  // jwt 包含当前包含的员工信息
            return Result.success(jwt);
        }

        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }

    /**
     * 家政人员登录
     * @param worker 家政人员对象
     * @return 结果
     */
    @PostMapping("/workerLogin")
    public Result workerLogin(@RequestBody Worker worker) {
        Worker user = workerService.login(worker);

        // 登录成功，生成令牌，下发令牌
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();

            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("identity", 2);  // 身份：1，消费者；2，家政人员；3，管理员

            String jwt = JwtUtils.generateJwt(claims);  // jwt 包含当前包含的员工信息
            return Result.success(jwt);
        }

        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }

    /**
     * 管理员登录
     * @param adminUser 管理员对象
     * @return 结果
     */
    @PostMapping("/adminUserLogin")
    public Result consumerUserLogin(@RequestBody AdminUser adminUser) {
        AdminUser user = adminUserService.login(adminUser);

        // 登录成功，生成令牌，下发令牌
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();

            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("identity", 3);  // 身份：1，消费者；2，家政人员；3，管理员

            String jwt = JwtUtils.generateJwt(claims);  // jwt 包含当前包含的员工信息
            return Result.success(jwt);
        }

        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
