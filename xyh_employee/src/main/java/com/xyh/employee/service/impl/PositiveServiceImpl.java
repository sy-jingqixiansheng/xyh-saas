package com.xyh.employee.service.impl;

import com.xyh.employee.dao.EmployeePositiveMapper;
import com.xyh.employee.service.PositiveService;
import cpm.xyh.entity.employee.EmployeePositive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 9:00
 * @Version 1.0
 */
@Service
public class PositiveServiceImpl implements PositiveService {

    @Autowired
    private EmployeePositiveMapper positiveMapper;
    @Override
    public void save(EmployeePositive positive) {
        positiveMapper.insert(positive);
    }

    @Override
    public EmployeePositive findById(String uid) {
        return positiveMapper.selectByPrimaryKey(uid);
    }
}
