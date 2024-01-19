package com.chenyang.ducumentmanagement.service.impl;


import com.chenyang.ducumentmanagement.mapper.CategoryMapper;
import com.chenyang.ducumentmanagement.pojo.Category;
import com.chenyang.ducumentmanagement.service.CategoryService;
import com.chenyang.ducumentmanagement.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        //query the list owned by the current user
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }
}
