package com.xyh.employee.service;


import cpm.xyh.entity.employee.UserCompanyPersonal;
import cpm.xyh.entity.employee.response.EmployeeReportResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 8:58
 * @Version 1.0
 */
public interface UserCompanyPersonalService {
    void save(UserCompanyPersonal sourceInfo);

    UserCompanyPersonal findById(String uid);

    List<EmployeeReportResult> findByReport(@Param("companyId") String companyId,@Param("month") String month);
}
