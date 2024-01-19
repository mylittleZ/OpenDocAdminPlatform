package com.chenyang.ducumentmanagement.mapper;

import com.chenyang.ducumentmanagement.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
    "value(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    @Update("update article set title=#{title},category_id=#{categoryId},content=#{content},cover_img=#{coverImg},state=#{state} where id=#{id}")
    void update(Article article);


    @Select("select * from article where id=${id}")
    Article findById(Integer id);
}
