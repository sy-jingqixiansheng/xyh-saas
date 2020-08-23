package com.xyh.company;

import com.xyh.common.utils.IdWorker;
import com.xyh.company.dao.CompanyMapper;

import cpm.xyh.entity.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyTest {

    @Autowired
    private CompanyMapper companyDao;
    @Autowired
    private IdWorker idWorker;

    @Test
    public void test(){
        Company c = companyDao.findById("313");
        System.out.println(c);
//        Company company = new Company();
//        company.setId(idWorker.nextId()+"");
//        company.setName("adasd");
//        company.setManagerId("153151");
//        company.setState(1);
//        company.setBalance(new BigDecimal("4143"));
//        company.setCreateTime(new Date());
//        companyDao.save(company);

    }
}
