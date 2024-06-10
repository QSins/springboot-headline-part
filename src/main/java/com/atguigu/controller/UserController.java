package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author：qinshixin
 * @Date：2024/6/10 下午12:56
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController
{
    @Resource
    JwtHelper jwtHelper;

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result<Map<String, String>>  login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/getUserInfo")
    public Result<Map<String, User>> getUserInfo(@RequestHeader String token)
    {
        return userService.getUserInfo(token);
    }
}
