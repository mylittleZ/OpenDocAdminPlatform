package com.chenyang.ducumentmanagement.service.impl;

import com.chenyang.ducumentmanagement.mapper.ArticleMapper;
import com.chenyang.ducumentmanagement.pojo.Article;
import com.chenyang.ducumentmanagement.service.ArticleService;
import com.chenyang.ducumentmanagement.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =(Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }
}
