package com.example.homemanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 订单表
 */
public class Order {
    private Integer id; // 订单ID
    private Integer userId; // 用户id（外键）
    private Integer goodsId;    // 服务商品id（外键）
    private Integer workerId;   // 家政人员id（外键）
    private Short status;   // 订单状态（1 已完成 0 未完成）

    private String username;    // 用户名

    private String workerName;  // 家政人员姓名

    private String goodsName;   // 商品名
    private String goodsPrice;  // 商品价格

    private LocalDateTime createTime;   // 创建时间
}
