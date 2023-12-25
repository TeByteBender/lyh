package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface OrderService {

    /**
     * 分页展示用户订单
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 每页大小
     * @return
     */
    PageBean userListOrder(Integer userId, int page, Integer pageSize);

    /**
     * 分页展示所有订单
     * @param page 页数
     * @param pageSize 每页展示的条数
     * @return
     */
    PageBean getAllOrder(Integer page, Integer pageSize);

    /**
     * 删除订单
     * @param id 订单id
     */
    void deleteOrder(Integer id);


}
