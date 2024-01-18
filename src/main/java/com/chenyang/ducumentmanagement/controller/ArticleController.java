package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Result;
import com.chenyang.ducumentmanagement.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    //从header中拿token
    public Result<String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response){
        //verify token
//        try {
//            Map<String,Object> claims = JwtUtil.parseToken(token);
//            return Result.success("list data");
//        } catch (Exception e) {
//            response.setStatus(401);
//            return Result.error("Not Logged In");
//        }
        return Result.success("1111");
    }
}
