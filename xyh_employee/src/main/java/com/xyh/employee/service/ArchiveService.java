package com.xyh.employee.service;


import com.xyh.common.entity.PageVO;
import cpm.xyh.entity.employee.EmployeeArchive;


import java.util.HashMap;


/**
 * @Author SQ
 * @Date 2020/8/17 0017 8:57
 * @Version 1.0
 */
public interface ArchiveService {


    PageVO<EmployeeArchive> findSearch(HashMap<String, Object> mapPPY, Integer page, Integer pagesize);
}
