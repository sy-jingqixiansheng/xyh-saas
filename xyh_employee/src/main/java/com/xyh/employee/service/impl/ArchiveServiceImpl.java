package com.xyh.employee.service.impl;


import com.github.pagehelper.PageHelper;
import com.xyh.common.entity.PageUtil;
import com.xyh.common.entity.PageVO;
import com.xyh.employee.dao.EmployeeArchiveMapper;

import com.xyh.employee.service.ArchiveService;
import cpm.xyh.entity.employee.EmployeeArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
 * @Author SQ
 * @Date 2020/8/17 0017 8:59
 * @Version 1.0
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private EmployeeArchiveMapper archiveMapper;

    @Override
    public PageVO<EmployeeArchive> findSearch(HashMap<String, Object> mapPPY, Integer page, Integer pagesize) {
        PageHelper.startPage(page,pagesize);
        List<EmployeeArchive> archiveList = archiveMapper.findArchiveByMap(mapPPY);
        return PageUtil.getPageVO(archiveList);
    }


}
