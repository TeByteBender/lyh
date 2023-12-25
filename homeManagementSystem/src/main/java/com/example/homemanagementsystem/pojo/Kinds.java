package com.example.homemanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 服务种类
 */
public class Kinds {
    private Integer id; // 服务类型id
    private String name;    // 类型名称
}
