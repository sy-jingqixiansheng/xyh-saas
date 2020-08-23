package com.xyh.employee.service.impl;

import com.xyh.employee.dao.UserCompanyJobsMapper;
import com.xyh.employee.service.UserCompanyJobsService;
import cpm.xyh.entity.employee.UserCompanyJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 9:01
 * @Version 1.0
 */
@Service
public class UserCompanyJobsServiceImpl implements UserCompanyJobsService {

    @Autowired
    private UserCompanyJobsMapper userCompanyJobsMapper;
    @Override
    public void save(UserCompanyJobs sourceInfo) {
        userCompanyJobsMapper.insert(sourceInfo);
    }

    @Override
    public UserCompanyJobs findById(String uid) {
        return userCompanyJobsMapper.selectByPrimaryKey(uid);
    }
}
