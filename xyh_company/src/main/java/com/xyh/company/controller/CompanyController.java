package com.xyh.company.controller;

import com.xyh.common.entity.Result;
import com.xyh.common.entity.ResultCode;

import com.xyh.company.service.CompanyService;
import cpm.xyh.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 根据企业id查询企业信息
     *
     * @param id 企业id
     * @return
     */
    @GetMapping("/findByid/{id}")
    public Result findById(@PathVariable String id) {
        Company company = companyService.findById(id);
        return new Result(ResultCode.SUCCESS, company);
    }

    /**
     * 更新企业
     */
    @PutMapping("/update")
    public Result update(@RequestBody Company company) {

        companyService.update(company);
        return Result.SUCCESS();

    }

    /**
     * 添加企业
     */
    @PostMapping("/add")
    public Result add(@RequestBody Company company) {
        companyService.addCompanyNotNUll(company);
        return Result.SUCCESS();
    }

    /**
     * 根据id删除企业
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        companyService.deleteById(id);
        return Result.SUCCESS();
    }

    /**
     * 获取企业列表
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<Company> companyList = companyService.findAll();
        return new Result(ResultCode.SUCCESS, companyList);
    }

}
