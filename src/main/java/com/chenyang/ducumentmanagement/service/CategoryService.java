package com.chenyang.ducumentmanagement.service;

import com.chenyang.ducumentmanagement.pojo.Category;

import java.util.List;

public interface CategoryService {
    //new category
    void add(Category category);

    //query category list
    List<Category> list();

    //query category by id
    Category findById(Integer id);

    void update(Category category);
}
