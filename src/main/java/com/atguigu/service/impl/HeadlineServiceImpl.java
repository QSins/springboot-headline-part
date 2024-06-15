package com.atguigu.service.impl;

import com.atguigu.pojo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author qinshixin
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-06-09 23:58:57
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    private final HeadlineMapper headlineMapper;

    public HeadlineServiceImpl(HeadlineMapper headlineMapper)
    {
        this.headlineMapper = headlineMapper;
    }

    @Override
    public Result<Map<String, Object>> findNewsPage(PortalVo portalVo)
    {
        IPage<Map<String, String>> page = new Page<>(portalVo.getPageNum(),
                portalVo.getPageSize());
        headlineMapper.selectMyPage(page, portalVo);
        Map<String, Object> data = new HashMap<>();
        data.put("pageData",page.getRecords());
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageInfo", data);
        return Result.ok(pageInfo);
    }

    @Override
    public Result<Map<String, Object>> showHeadlineDetail(Integer hid)
    {
        // 根据hid查询数据
        Map data = headlineMapper.queryDetailMap(hid);
        HashMap<String, Object> headlineMap = new HashMap<>();
        headlineMap.put("headline", data);

        // 修改阅读量 + 1
        Headline headline = new Headline();
        headline.setHid((Integer)(data.get("hid")));
        headline.setVersion((Integer)(data.get("version")));
        headline.setPageViews((Integer)(data.get("pageViews")) + 1);
        headlineMapper.updateById(headline);
        return Result.ok(headlineMap);
    }
}




