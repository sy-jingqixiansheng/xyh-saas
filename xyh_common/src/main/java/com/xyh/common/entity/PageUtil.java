package com.xyh.common.entity;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 11:55
 * @Version 1.0
 */
public class PageUtil {
    private PageUtil(){}
    public static <T> PageVO<T> getPageVO(List<T> list){
        PageVO<T> pageVo=new PageVO<>();
        if(list instanceof Page){
            Page<T> page= (Page<T>) list;
            pageVo.setTotal(page.getTotal());
            pageVo.setTotalPages(page.getPages());
            pageVo.setPageNum(page.getPageNum());
            pageVo.setCurPageSize(page.getPageSize());
            pageVo.setPageSize(page.size());
            pageVo.setRows(page.getResult());
        }
        return pageVo;
    }
}
