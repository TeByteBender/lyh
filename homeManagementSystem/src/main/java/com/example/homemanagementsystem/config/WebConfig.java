package com.example.homemanagementsystem.config;

import com.example.homemanagementsystem.interceptor.AdminUserLoginCheckInterceptor;
import com.example.homemanagementsystem.interceptor.ConsumerUserLoginCheckInterceptor;
import com.example.homemanagementsystem.interceptor.WorkerLoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ConsumerUserLoginCheckInterceptor consumerUserLoginCheckInterceptor;

    @Autowired
    private WorkerLoginCheckInterceptor workerLoginCheckInterceptor;

    @Autowired
    private AdminUserLoginCheckInterceptor adminUserLoginCheckInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns()：需要拦截哪些资源
        // excludePathPatterns()：不需要拦截哪些资源consumerUser
        registry.addInterceptor(consumerUserLoginCheckInterceptor).addPathPatterns("/consumerUser/**").excludePathPatterns("/login/**", "/register");  // 拦截所有资源
        registry.addInterceptor(workerLoginCheckInterceptor).addPathPatterns("/worker/**").excludePathPatterns("/login/**", "/register");  // 拦截所有资源
        registry.addInterceptor(adminUserLoginCheckInterceptor).addPathPatterns("/adminUser/**").excludePathPatterns("/login/**", "/register");  // 拦截所有资源
    }

    // 解决跨域问题
    // springboot 2.4以上不能使用allowedOrigins("*")，要改成 .allowedOriginPatterns("*")
    /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
     */
}
