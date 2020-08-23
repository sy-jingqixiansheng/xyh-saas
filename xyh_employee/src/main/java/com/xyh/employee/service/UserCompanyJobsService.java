package com.xyh.employee.service;


import cpm.xyh.entity.employee.UserCompanyJobs;
import org.apache.ibatis.annotations.Param;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 8:58
 * @Version 1.0
 */
public interface UserCompanyJobsService {
    void save(UserCompanyJobs sourceInfo);

    UserCompanyJobs findById(@Param("id") String uid);
}
