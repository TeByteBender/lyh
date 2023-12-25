package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.AdminUserMapping;
import com.example.homemanagementsystem.mapper.ConsumerUserMapper;
import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.service.AdminUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapping adminUserMapping;

    @Autowired
    private ConsumerUserMapper consumerUserMapper;

    @Override
    public AdminUser login(AdminUser adminUser) {
        return adminUserMapping.getUserByUsernameAndPassword(adminUser);
    }


}
