package com.example.homemanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 服务商品
 */
public class Goods {
    private Integer id; // 服务商品ID
    private Integer KindsId;    // 服务种类id（外键）
    private String goodName;    // 商品名
    private double price;   // 服务价格
    private String image;   // 图片
}
