package com.xyh.employee.dao;


import cpm.xyh.entity.employee.EmployeeArchive;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;


@Repository
public interface EmployeeArchiveMapper {

    int deleteByPrimaryKey(String id);


    int insert(EmployeeArchive record);


    int insertSelective(EmployeeArchive record);


    EmployeeArchive selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(EmployeeArchive record);



    int updateByPrimaryKey(EmployeeArchive record);


    List<EmployeeArchive> findArchiveByMap(@Param("archMap") HashMap<String, Object> mapPPY);
}