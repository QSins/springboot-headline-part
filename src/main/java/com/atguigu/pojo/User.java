package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName news_user
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private Integer uid;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户登录密码密文
     */
    private String userPwd;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 头条是否被删除 1 删除  0 未删除
     */
    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}