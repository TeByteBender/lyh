package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminUserTest {

    @Autowired
    private AdminUserMapping adminUserMapping;

    @Test
    public void testAdminLogin() {
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername("guanliyuan");
        adminUser.setPassword("123456");

        AdminUser user = adminUserMapping.getUserByUsernameAndPassword(adminUser);
        System.out.println(user);
    }
}
