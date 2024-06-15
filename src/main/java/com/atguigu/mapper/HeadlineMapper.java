package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author qinshixin
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-06-09 23:58:57
* @Entity com.atguigu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map<String, String>> selectMyPage (IPage<Map<String, String>> page,
                                             @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(@Param("hid") Integer hid);
}




