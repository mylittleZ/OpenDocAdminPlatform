package com.chenyang.ducumentmanagement.controller;

import com.chenyang.ducumentmanagement.pojo.Result;
import com.chenyang.ducumentmanagement.pojo.User;
import com.chenyang.ducumentmanagement.service.UserService;
import com.chenyang.ducumentmanagement.utils.JwtUtil;
import com.chenyang.ducumentmanagement.utils.Md5Util;
import com.chenyang.ducumentmanagement.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")  // 该类中的所有方法都将默认继承这个路径，以 /user 开头
@Validated
public class UserController {

    // 因为查询是service做的事，所以这里要引入service对象
    @Autowired
    private UserService userService;
    @PostMapping("register") ///user/register 的 POST 请求
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
            //查询用户
      User user =  userService.findByUserName(username);
      if(user==null){
          // 注册
          userService.register(username,password);
          return Result.success();
      }else{
          // 被占用
          return Result.error("Username already exists");
      }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User loginUser = userService.findByUserName(username);
        //判断用户是否存在
        if(loginUser==null){
            return Result.error("Username error");
        }
        //判断密码是否正确 loginUser对象中的password是密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("Password error");
    }

//    @GetMapping("/userInfo")
//    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
//        Map<String,Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
//        User user = userService.findByUserName(username);
//        return Result.success(user);
//    }


    // 使用ThreadLocal进行优化
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    // 接收的参数是{"old_pwd": "value1", "new_pwd": "value2"}，所以用@RequestBody Map<String,String> params
    public Result updatePwd(@RequestBody Map<String,String> params){
        //手动校验参数，因为validator的不一定全部满足
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("Missing required parameters");
        }
        // 通过service调用findusername来获取原密码，来和oldpwd比对是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("Old Password is incorrect");
        }
        //校验newPwd和rePwd是否一样
        if (!newPwd.equals(rePwd)){
            return Result.error("Confirmed Password is incorrect");
        }
        // 调用service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
