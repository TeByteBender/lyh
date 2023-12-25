package com.example.homemanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 管理员
 */
public class AdminUser {
    private Integer id; // 管理员id
    private String username;    // 用户名
    private String password;    // 密码
}
