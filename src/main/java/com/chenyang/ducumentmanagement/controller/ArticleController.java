package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Article;
import com.chenyang.ducumentmanagement.pojo.PageBean;
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

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,Integer pageSize,@RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String state){
        PageBean<Article> articlePageBean= articleService.list(pageNum,pageSize,categoryId,state);
        return  Result.success(articlePageBean);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id){
        Article article = articleService.findById(id);
        return Result.success(article);
    }
}
