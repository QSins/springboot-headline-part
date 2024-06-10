package com.atguigu.test;

import com.atguigu.utils.JwtHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author：qinshixin
 * @Date：2024/6/10 上午11:09
 */
@SpringBootTest
@Slf4j
public class JwtTest
{
    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test() {
        // 生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        log.info("#################生成 传入用户标识####################");
        log.info(">>>>>>>>>>>>>>>>>token:{}", token);
        log.info("#####################################");

        // 解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        log.info("#################生成 传入用户标识####################");
        log.info(">>>>>>>>>>>>>>>>>>>>userId:{}", userId);
        log.info("#####################################");

        // 校验token是否到期
        boolean expiration = jwtHelper.isExpiration(token);
        log.info("#################校验token是否到期####################");
        log.info(">>>>>>>>>>>>>>>>>>>>expiration:{}", expiration);
        log.info("#####################################");
    }
}
