package com.chenyang.ducumentmanagement.interceptors;

import com.chenyang.ducumentmanagement.pojo.Result;
import com.chenyang.ducumentmanagement.utils.JwtUtil;
import com.chenyang.ducumentmanagement.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //verify token
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object> claims = JwtUtil.parseToken(token);
            // 把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true; //放行
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
