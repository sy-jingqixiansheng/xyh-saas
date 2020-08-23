package com.xyh.company.service.impl;

import com.xyh.common.utils.IdWorker;
import com.xyh.company.dao.CompanyMapper;
import com.xyh.company.service.CompanyService;
import cpm.xyh.entity.company.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * 根据企业id查询
     *
     * @param id
     * @return
     */
    @Override
    public Company findById(String id) {
        Company company = companyMapper.findById(id);
        return company;
    }

    @Override
    public void addCompanyNotNUll(Company company) {
        company.setId(idWorker.nextId()+"");
        company.setCreateTime(new Date());
        company.setState(1);
        company.setAuditState("0");
        company.setBalance(25050.00);
        companyMapper.addCompanyNotNUll(company);
    }

    /**
     * 根据id删除企业
     * @param id
     */
    @Override
    public void deleteById(String id) {
        companyMapper.deleteById(id);
    }

    /**
     * 获取企业列表
     */
    @Override
    public List<Company> findAll() {
        return companyMapper.findAll();
    }


    /**
     * 更新企业
     */
    @Override
    public void update(Company company) {
        Company companyNew = new Company();
        BeanUtils.copyProperties(company,companyNew);
        companyMapper.update(companyNew);
    }
}


