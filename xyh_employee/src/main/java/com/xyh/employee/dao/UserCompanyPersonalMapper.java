package com.xyh.employee.dao;


import cpm.xyh.entity.employee.UserCompanyPersonal;
import cpm.xyh.entity.employee.response.EmployeeReportResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompanyPersonalMapper {

    int deleteByPrimaryKey(String userId);


    int insert(UserCompanyPersonal record);


    int insertSelective(UserCompanyPersonal record);


    UserCompanyPersonal selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(UserCompanyPersonal record);



    int updateByPrimaryKey(UserCompanyPersonal record);

    List<EmployeeReportResult> findByReport(@Param("companyId") String companyId, @Param("month") String month);
}