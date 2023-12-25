package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 查询当前用户的订单
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> getAllUserOrder(Integer userId);

    /**
     * 查询所有订单
     * @return List<Order>
     */
    List<Order> selectAll();

    /**
     * 通过订单id删除订单
     * @param id 订单id
     */
    @Delete("delete from `order` where id = #{id}")
    void deleteOrderById(Integer id);

    /**
     * 修改订单状态
     * @param status 状态（0 未完成，1 已完成）
     */
    @Update("update `order` set status = #{status}")
    void updateStatus(Integer status);
}
