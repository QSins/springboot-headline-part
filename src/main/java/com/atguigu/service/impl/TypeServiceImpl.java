package com.atguigu.service.impl;

import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Type;
import com.atguigu.service.TypeService;
import com.atguigu.mapper.TypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author qinshixin
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-06-09 23:58:57
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Resource
    private TypeMapper typeMapper;

    @Override
    public Result<List<Type>> findAllTypes()
    {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}




