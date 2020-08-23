package com.xyh.company.service;



import cpm.xyh.entity.company.Company;

import java.util.List;

/**
 * 企业数据访问接口
 */
public interface CompanyService {

    /**
     * 根据id查询企业
     * @param id
     * @return
     */
    Company findById(String id);

    /**
     * 添加企业
     */
    void addCompanyNotNUll(Company company);
    /**
     * 根据id删除企业
     */
    void deleteById(String id);
    /**
     * 获取企业列表
     */
    List<Company> findAll();

    /**
     * 更新企业
     */
    void update(Company company);
}
