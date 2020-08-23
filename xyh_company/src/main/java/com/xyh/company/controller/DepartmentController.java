package com.xyh.company.controller;

import com.xyh.common.controller.BaseController;
import com.xyh.common.entity.Result;
import com.xyh.common.entity.ResultCode;
import com.xyh.common.exception.CommonException;
import com.xyh.company.service.CompanyService;
import com.xyh.company.service.DepartmentService;
import cpm.xyh.entity.company.Company;
import cpm.xyh.entity.company.Department;
import cpm.xyh.entity.company.response.DeptListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/11 0011 10:06
 * @Version 1.0
 */
//@CrossOrigin
@RestController
@RequestMapping("/company")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;
    /**
     * 添加部门
     */
    @PostMapping("/department/addDepartments")
    public Result add(@RequestBody Department department)  {
        department.setCompanyId(companyId);
        departmentService.save(department);
        return Result.SUCCESS();
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/department/findById/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 根据ID删除部门
     */
    @DeleteMapping("/department/delete/{id}")
    public Result delete(@PathVariable String id){
        departmentService.deleteById(id);
        return Result.SUCCESS();
    }

    /**
     * 根据企业id获取对应部门列表
     */
    @GetMapping("/department/findAllDepartment")
    public Result findAll(){
        Company company = companyService.findById(companyId);
        List<Department> departments = departmentService.findALl(company.getId());
        DeptListResult listResult = new DeptListResult(company, departments);
        return new Result(ResultCode.SUCCESS,listResult);
    }

    /**
     * 修改部门信息
     */
    @RequestMapping(value = "/department/updateDepartment/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Department
            department){
        department.setCompanyId(companyId);
        department.setId(id);
        departmentService.update(department);
        return Result.SUCCESS();
    }

    /**
     *服务调用(根据部门code,companyId查询部门)
     */
    @RequestMapping(value = "/department/search", method = RequestMethod.POST)
    public Department findByCode(@RequestParam("code") String code,@RequestParam("companyId") String companyId){
        Department department = departmentService.findByCode(code,companyId);
        return department;
    }
}
