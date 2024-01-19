package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Category;
import com.chenyang.ducumentmanagement.pojo.Result;
import com.chenyang.ducumentmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //new category
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    //query category list
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id){
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        categoryService.delete(id);
        return Result.success();
    }

}
