package com.atguigu.pojo;

import lombok.Data;

/**
 * @Author：qinshixin
 * @Date：2024/6/10 下午6:28
 */
@Data
public class PortalVo
{
    private String keyWords;

    private int type = 0;

    private int pageNum = 1;

    private int pageSize = 10;
}
