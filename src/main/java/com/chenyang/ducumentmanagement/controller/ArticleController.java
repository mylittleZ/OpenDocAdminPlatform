package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Article;
import com.chenyang.ducumentmanagement.pojo.Result;
import com.chenyang.ducumentmanagement.service.ArticleService;
import com.chenyang.ducumentmanagement.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @PostMapping()
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
}
