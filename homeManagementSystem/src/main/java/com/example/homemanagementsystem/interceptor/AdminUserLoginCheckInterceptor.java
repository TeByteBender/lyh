package com.example.homemanagementsystem.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AdminUserLoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url：{}", url);

        // 2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作，放行");
            return true;
        }

        // 3. 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        System.out.println("jwt令牌：" + jwt);

        // 4. 判断令牌是否存在，如果存在，返回错误结果（未登录）
        // StringUtils.hasLength(String str)：判断字符串是否有长度
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");

            // 手动转换对象 -- json ========> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            // 将json字符串响应给前端页面
            response.getWriter().write(notLogin);
            return false;
        }

        int id;

        // 5. 解析 token。如果解析失败，返回错误结果
        try {
            JwtUtils.parseJWT(jwt);

            Map<String, Object> claims = new HashMap<>();
            claims = JwtUtils.parseJWT(jwt);  // jwt 包含当前包含的员工信息

            id = (Integer) claims.get("identity");    // 得到用户身份
        } catch (Exception e) {
            e.printStackTrace();

            log.info("解析令牌失败，返回未登录的错误信息");

            Result error = Result.error("NOT_LOGIN");

            // 手动转换 -- json ==========> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            // 将 json 字符串响应给前端页面
            response.getWriter().write(notLogin);
            return false;
        }

        // 判断用户身份是否合法
        if (id != 3) {
            Result error = Result.error("access denied");

            // 手动转换 -- json ==========> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            // 将 json 字符串响应给前端页面
            response.getWriter().write(notLogin);

            return false;
        }


        // 6. 放行
        log.info("令牌合法，放行");
        return true;
    }

    // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
