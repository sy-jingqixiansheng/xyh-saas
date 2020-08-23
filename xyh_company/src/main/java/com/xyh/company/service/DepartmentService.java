package com.xyh.company.service;



import cpm.xyh.entity.company.Department;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/11 0011 10:28
 * @Version 1.0
 */
public interface DepartmentService {

    void save(Department department);

    /**
     * 根据id查询
     */
    Department findById(String id);

    /**
     * 根据ID删除部门
     */
    void deleteById(String id);

    /**
     * 根据企业id获取对应部门列表
     */
    List<Department> findALl(String parseCompanyId);

    /**
     * 修改部门信息
     */
    void update(Department department);

    Department findByCode(String code, String companyId);
}
