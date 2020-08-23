package com.xyh.company.service.impl;

import com.xyh.common.utils.IdWorker;
import com.xyh.company.dao.DepartmentMapper;

import com.xyh.company.service.DepartmentService;
import cpm.xyh.entity.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @Author SQ
 * @Date 2020/8/11 0011 10:30
 * @Version 1.0
 */

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void save(Department department) {
        //填充其他参数
        department.setId(idWorker.nextId() + "");
        department.setCreateTime(new Date());
        departmentMapper.save(department);
    }

    @Override
    public Department findById(String id) {
        return departmentMapper.findDepartment(id);
    }

    @Override
    public void deleteById(String id) {
        departmentMapper.deleteById(id);
    }

    @Override
    public List<Department> findALl(String parseCompanyId) {
        List<Department> departments = departmentMapper.findAll(parseCompanyId);
        return departments;
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateDepartment(department);

    }

    /**
     *服务调用(根据部门code,companyId查询部门)
     */
    @Override
    public Department findByCode(String code, String companyId) {
        return departmentMapper.findByCodeCompanyId(code,companyId);
    }
}
