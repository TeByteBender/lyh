package com.example.homemanagementsystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.ConsumerUserService;
import com.example.homemanagementsystem.utils.JwtUtils;
import com.example.homemanagementsystem.utils.QiniuKodoUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerUserController {

    @Autowired
    private ConsumerUserService consumerUserService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

    /**
     * 获取当前用户的个人信息
     * @param token token令牌
     * @return 结果
     */
    @GetMapping("/consumerUser/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {

        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        ConsumerUser consumerUser = consumerUserService.getUserInfo(id);

        return Result.success(consumerUser);
    }

    /**
     * 分页查询所有用户信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/adminUser/getAllConsumerUser")
    public Result getAllConsumerUser(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = consumerUserService.getAllConsumerUser(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 修改个人信息
     * @param token token令牌
     * @param consumerUser 用户对象
     * @return
     */
    @PutMapping("/consumerUser/editUserInfo")
    public Result editUserInfo(@RequestHeader String token, @RequestBody ConsumerUser consumerUser) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        consumerUser.setId(id);

        ConsumerUser user = consumerUserService.editUserInfo(consumerUser);

        return Result.success(user);
    }

    /**
     * 修改密码
     * @return
     */
    @PutMapping("/consumerUser/editPassword")
    public Result editPassword(Integer id, String password) {
        return consumerUserService.editPassword(id, password);
    }

    /**
     * 充值
     * @param token 令牌
     * @param password 密码
     * @param topUpMoney 充值的金额
     * @return
     */
    @PutMapping("/consumerUser/topUp")
    public Result topUp(@RequestHeader String token, @RequestParam String password, @RequestParam String topUpMoney) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        // 充值
        consumerUserService.topUp(id, password, topUpMoney);
        // 获取新的用户信息
        ConsumerUser consumerUser = consumerUserService.getUserInfo(id);

        return Result.success(consumerUser);
    }

    /**
     * 支付
     * @param order 订单
     * @return Result
     */
    @PutMapping("/consumerUser/pay")
    public Result pay(@RequestBody Order order) {

        Result result = consumerUserService.pay(order);

        return result;
    }

    /**
     * 上传头像
     * @param token 令牌
     * @param image 图片
     * @return 结果
     * @throws IOException
     */
    @Transactional
    @PostMapping("/consumerUser/upload")
    public Result upload(@RequestHeader String token, MultipartFile image) throws IOException {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值
        // 获得用户信息
        ConsumerUser consumerUser = consumerUserService.getUserInfo(id);
        String oldUrl = consumerUser.getImage();

        // 上传文件
        String url = qiniuKodoUtils.upload(image);

        // 修改用户的image信息
        ConsumerUser user = consumerUserService.uploadImage(id, url);

        // 判断oldUrl是否为null或空
        if (!oldUrl.equals("http://s5ylvlikx.hd-bkt.clouddn.com/1.jpg")){
            // 删除七牛云中原本的文件
            qiniuKodoUtils.delete(oldUrl);
        }

        return Result.success(user);
    }
}
