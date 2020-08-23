package com.xyh.employee.dao;


import cpm.xyh.entity.employee.EmployeePositive;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePositiveMapper {

    int deleteByPrimaryKey(String userId);


    int insert(EmployeePositive record);


    int insertSelective(EmployeePositive record);


    EmployeePositive selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(EmployeePositive record);



    int updateByPrimaryKey(EmployeePositive record);
}