package com.atguigu.controller;

import com.atguigu.pojo.PortalVo;
import com.atguigu.pojo.Type;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author：qinshixin
 * @Date：2024/6/10 下午4:43
 */

@RestController
@RequestMapping("/portal")
@CrossOrigin
public class PortalController
{
    @Resource
    private TypeService typeService;

    @Resource
    private HeadlineService headlineService;

    @GetMapping("/findAllTypes")
    public Result<List<Type>> findAllTypes(){
        return typeService.findAllTypes();
    }

    @PostMapping("/findNewsPage")
    Result<Map<String, Object>> findNewsPage(@RequestBody PortalVo portalVo)
    {
        return headlineService.findNewsPage(portalVo);
    }

    @PostMapping("/showHeadlineDetail")
    Result<Map<String, Object>> showHeadlineDetail(@RequestParam Integer hid) {
        return headlineService.showHeadlineDetail(hid);
    }
}
