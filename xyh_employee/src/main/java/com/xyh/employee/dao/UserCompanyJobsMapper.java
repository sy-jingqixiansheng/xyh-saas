package com.xyh.employee.dao;


import cpm.xyh.entity.employee.UserCompanyJobs;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCompanyJobsMapper {

    int deleteByPrimaryKey(String userId);


    int insert(UserCompanyJobs record);


    int insertSelective(UserCompanyJobs record);


    UserCompanyJobs selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(UserCompanyJobs record);



    int updateByPrimaryKey(UserCompanyJobs record);
}