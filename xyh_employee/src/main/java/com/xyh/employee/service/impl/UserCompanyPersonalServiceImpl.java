package com.xyh.employee.service.impl;

import com.xyh.employee.dao.UserCompanyPersonalMapper;
import com.xyh.employee.service.UserCompanyPersonalService;
import cpm.xyh.entity.employee.UserCompanyPersonal;
import cpm.xyh.entity.employee.response.EmployeeReportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 9:01
 * @Version 1.0
 */


@Service
@Transactional
public class UserCompanyPersonalServiceImpl implements UserCompanyPersonalService {

    @Autowired
    private UserCompanyPersonalMapper userCompanyPersonalMapper;

    @Override
    public void save(UserCompanyPersonal sourceInfo) {
        userCompanyPersonalMapper.insert(sourceInfo);
    }

    @Override
    public UserCompanyPersonal findById(String uid) {
        return userCompanyPersonalMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<EmployeeReportResult> findByReport(String companyId, String month) {
        return userCompanyPersonalMapper.findByReport(companyId,month+"%");
    }
}
