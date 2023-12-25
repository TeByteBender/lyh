package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.PageBean;

public interface AdminUserService {
    /**
     * 管理员登录
     * @param adminUser 管理员对象
     * @return 管理员对象
     */
    AdminUser login(AdminUser adminUser);


}
