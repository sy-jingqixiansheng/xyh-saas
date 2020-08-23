package com.xyh.company.dao;


import cpm.xyh.entity.company.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/11 0011 10:32
 * @Version 1.0
 */
public interface DepartmentMapper {

    void save(Department department);


    Department findDepartment(String id);

    void deleteById(String id);

    List<Department> findAll(String parseCompanyId);

    void updateDepartment(Department department);

    Department findByCodeCompanyId(@Param("code") String code,@Param("companyId") String companyId);
}
