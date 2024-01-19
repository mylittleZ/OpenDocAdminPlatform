package com.chenyang.ducumentmanagement.service;

import com.chenyang.ducumentmanagement.pojo.Article;
import com.chenyang.ducumentmanagement.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void update(Article article);

    Article findById(Integer id);

    void delete(Integer id);
}
