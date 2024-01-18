package com.chenyang.ducumentmanagement.service.impl;

import com.chenyang.ducumentmanagement.mapper.UserMapper;
import com.chenyang.ducumentmanagement.pojo.User;
import com.chenyang.ducumentmanagement.service.UserService;
import com.chenyang.ducumentmanagement.utils.Md5Util;
import com.chenyang.ducumentmanagement.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

// 接口与实现分离：这种结构体现了设计原则中的接口与实现分离。你定义一个接口，在 service 包
// 然后在 impl 包中提供具体的实现。这样做可以提高代码的模块化和可读性

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //把加密后的字符串传给后端
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
