package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;

public interface ConsumerUserService {
    /**
     * 登录
     * @param consumerUser 用户对象
     * @return 用户对象
     */
    ConsumerUser login(ConsumerUser consumerUser);

    /**
     * 注册
     * @param consumerUser 用户对象
     * @return Result
     */
    Result register(ConsumerUser consumerUser);

    /**
     * 查询用户信息
     * @param id 用户id
     * @return
     */
    ConsumerUser getUserInfo(Integer id);

    /**
     * 分页查询所有消费者用户
     * @param page 页码
     * @param pageSize 每页大小
     * @return
     */
    PageBean getAllConsumerUser(Integer page, Integer pageSize);

    /**
     * 修改个人信息
     * @param consumerUser 用户对象
     * @return ConsumerUser
     */
    ConsumerUser editUserInfo(ConsumerUser consumerUser);

    /**
     * 修改密码
     * @param id 用户id
     * @param password 密码
     * @return 结果
     */
    Result editPassword(Integer id, String password);

    /**
     * 上传图片
     *
     * @param id
     * @param url url路径
     */
    ConsumerUser uploadImage(Integer id, String url);

    /**
     * 充值
     * @param id 用户id
     * @param password 密码
     * @param topUpMoney 充值金额
     * @return Result
     */
    Result topUp(Integer id, String password, String topUpMoney);

    /**
     * 支付
     * @param order 订单
     * @return Result
     */
    Result pay(Order order);

    /**
     * 查看所有空闲的家政人员
     * @return 家政人员列表
     */
    // List<Worker> browseLeisureWorker();
}
