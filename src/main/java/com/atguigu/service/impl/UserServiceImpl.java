package com.atguigu.service.impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinshixin
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-06-09 23:58:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService
{

    private final UserMapper userMapper;
    private final JwtHelper jwtHelper;

    public UserServiceImpl(UserMapper userMapper, JwtHelper jwtHelper)
    {
        this.userMapper = userMapper;
        this.jwtHelper = jwtHelper;
    }

    /**
     * 登录业务
     * 1.根据账号，查询用户对象  - loginUser
     * 2.如果用户对象为null，查询失败，账号错误！ 501
     * 3.对比，密码 ，失败 返回503的错误
     * 4.根据用户id生成一个token, token -> result 返回
     *
     * @param user
     * @return
     */
    @Override
    public Result<Map<String, String>> login(User user)
    {
        // 根据账号查询数据
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(userLambdaQueryWrapper);

        // 用户不存在，报错返回
        if (loginUser == null)
        {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        // 对比密码
        if (StringUtils.isNotEmpty(loginUser.getUserPwd())
                && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd())))
        {
            // 登录成功
            // 根据用户id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            // 将token封装到result返回
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            return Result.ok(data);
        }

        // 密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    /**
     * 根据token获取用户数据
     * 1. token是否在有效期
     * 2. 根据token解析userId
     * 3. 根据用户id查询用数据
     * 4. 去掉密码，封装result结果返回即可
     *
     * @param token
     * @return
     */
    @Override
    public Result<Map<String, User>> getUserInfo(String token)
    {
        // token为空或者token失效，报错返回
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token))
        {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userId);
        user.setUserPwd("");

        Map<String, User> data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    @Override
    public Result<Object> checkUsrName(String username)
    {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if (count == 0)
        {
            return Result.ok(null);
        }
        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result<Object> registry(User user)
    {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if (count > 0)
        {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }


}




