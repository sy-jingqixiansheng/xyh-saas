package com.xyh.company.dao;



import cpm.xyh.entity.company.Company;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyMapper {

    /**
     * 根据id查询企业
     */
    Company findById(String id);

    void addCompanyNotNUll(Company company);

    /**
     * 根据id删除企业
     * @param id
     */
    void deleteById(String id);


    List<Company> findAll();



    /**
     * 更新企业
     */
    void update(Company companyNew);
}
