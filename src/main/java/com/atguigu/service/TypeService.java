package com.atguigu.service;

import com.atguigu.pojo.Type;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author qinshixin
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-06-09 23:58:57
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有类别数据
     *
     * @return
     */
    Result<List<Type>> findAllTypes();
}
