package com.xyh.employee.dao;


import cpm.xyh.entity.employee.EmployeeTransferPosition;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTransferPositionMapper {

    int deleteByPrimaryKey(String userId);


    int insert(EmployeeTransferPosition record);


    int insertSelective(EmployeeTransferPosition record);


    EmployeeTransferPosition selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(EmployeeTransferPosition record);


    int updateByPrimaryKey(EmployeeTransferPosition record);
}