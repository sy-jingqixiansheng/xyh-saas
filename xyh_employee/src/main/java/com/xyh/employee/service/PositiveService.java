package com.xyh.employee.service;


import cpm.xyh.entity.employee.EmployeePositive;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 8:57
 * @Version 1.0
 */
public interface PositiveService {
    void save(EmployeePositive positive);

    EmployeePositive findById(String uid);
}
