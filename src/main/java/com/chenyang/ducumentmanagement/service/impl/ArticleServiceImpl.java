package com.chenyang.ducumentmanagement.service.impl;

import com.chenyang.ducumentmanagement.mapper.ArticleMapper;
import com.chenyang.ducumentmanagement.pojo.Article;
import com.chenyang.ducumentmanagement.pojo.PageBean;
import com.chenyang.ducumentmanagement.service.ArticleService;
import com.chenyang.ducumentmanagement.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> articlePageBean = new PageBean<>();

        //开启分页查询 Pagehelper mybatis插件,用了这个之后会自动把pageNum,pageSize插入到参数后面
        PageHelper.startPage(pageNum,pageSize);

        //调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =(Integer) map.get("id");
        List<Article> articleList = articleMapper.list(userId,categoryId,state);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的total和pageNum,所以把articleList强转了
        Page<Article> page = (Page<Article>) articleList;

        //把数据填充到PageBean对象中
        articlePageBean.setTotal(page.getTotal());
        articlePageBean.setItems(page.getResult());
        return articlePageBean;
    }

    @Override
    public void update(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =(Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.update(article);
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.findById(id);
    }
}
