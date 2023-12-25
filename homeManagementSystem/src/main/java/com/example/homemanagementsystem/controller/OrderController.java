package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.OrderService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页展示用户的所有订单信息
     * @return
     */
    @GetMapping("consumerUser/userListOrder")
    public Result UserlistOrder(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                Integer userId) {

        PageBean pageBean = orderService.userListOrder(userId, page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 分页查询所有订单信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return
     */
    @GetMapping("/adminUser/getAllOrder")
    public Result getAllOrder(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = orderService.getAllOrder(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 删除订单
     * @param id 订单id
     * @return 结果
     */
    @DeleteMapping("/deleteOrder")
    public Result deleteOrder(Integer id) {
        orderService.deleteOrder(id);
        return Result.success();
    }
}
