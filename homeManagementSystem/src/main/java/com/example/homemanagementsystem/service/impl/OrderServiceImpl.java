package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.OrderMapper;
import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageBean userListOrder(Integer userId, int page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Order> orders = orderMapper.getAllUserOrder(userId);
        Page<Order> p = (Page<Order>) orders;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public PageBean getAllOrder(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Order> orders = orderMapper.selectAll();
        Page<Order> p = (Page<Order>) orders;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void deleteOrder(Integer id) {
        orderMapper.deleteOrderById(id);
    }
}
