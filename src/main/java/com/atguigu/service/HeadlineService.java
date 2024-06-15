package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author qinshixin
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-06-09 23:58:57
*/
public interface HeadlineService extends IService<Headline> {
    /**
     * 首页数据查询
     *
     * @param portalVo
     * @return
     */
    Result<Map<String, Object>> findNewsPage(PortalVo portalVo);

    /**
     * 根据hid查询详情
     *
     * @param hid
     * @return
     */
    Result<Map<String, Object>> showHeadlineDetail(Integer hid);
}
