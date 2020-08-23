package com.xyh.employee.dao;


import cpm.xyh.entity.employee.EmployeeResignation;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeResignationMapper {

    int deleteByPrimaryKey(String userId);


    int insert(EmployeeResignation record);


    int insertSelective(EmployeeResignation record);


    EmployeeResignation selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(EmployeeResignation record);


    int updateByPrimaryKey(EmployeeResignation record);
}