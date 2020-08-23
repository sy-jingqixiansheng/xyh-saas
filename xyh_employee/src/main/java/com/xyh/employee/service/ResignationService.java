package com.xyh.employee.service;


import cpm.xyh.entity.employee.EmployeeResignation;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 8:57
 * @Version 1.0
 */
public interface ResignationService {
    void save(EmployeeResignation resignation);

    EmployeeResignation findById(String uid);
}
