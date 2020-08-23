package com.xyh.employee.service.impl;

import com.xyh.employee.dao.EmployeeResignationMapper;
import com.xyh.employee.service.ResignationService;
import cpm.xyh.entity.employee.EmployeeResignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 9:01
 * @Version 1.0
 */
@Service
public class ResignationServiceImpl implements ResignationService {

    @Autowired
    private EmployeeResignationMapper employeeResignationMapper;
    @Override
    public void save(EmployeeResignation resignation) {
        employeeResignationMapper.insert(resignation);
    }

    @Override
    public EmployeeResignation findById(String uid) {
        return employeeResignationMapper.selectByPrimaryKey(uid);
    }
}
