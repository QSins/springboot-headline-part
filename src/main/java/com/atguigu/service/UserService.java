package com.atguigu.service;

import com.atguigu.pojo.User;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author qinshixin
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-06-09 23:58:57
*/
public interface UserService extends IService<User> {

    /**
     * 登录业务
     * @param user
     * @return
     */
    Result<Map<String, String>>  login(User user);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    Result<Map<String, User>> getUserInfo(String token);

    /**
     * 检查账号名称是否可用
     *
     * @param username
     * @return
     */
    Result<Object> checkUsrName(String username);

    /**
     * 注册业务
     *
     * @param user
     * @return
     */
    Result<Object> registry(User user);
}
