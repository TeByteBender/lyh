package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapping {

    /**
     * 通过用户名和密码
     * @param adminUser 用户对象
     * @return 用户对象
     */
    @Select("select id, username, password from admin_user where username = #{username} and password = #{password}")
    AdminUser getUserByUsernameAndPassword(AdminUser adminUser);
}
