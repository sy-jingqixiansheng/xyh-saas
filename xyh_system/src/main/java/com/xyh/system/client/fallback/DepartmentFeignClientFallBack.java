package com.xyh.system.client.fallback;


import com.xyh.system.client.DepartmentFeignClient;
import cpm.xyh.entity.company.Department;
import org.springframework.stereotype.Service;

/**
 * @Author SQ  服务调用容错类
 * @Date 2020/8/19 0019 19:52
 * @Version 1.0
 */
@Service
public class DepartmentFeignClientFallBack implements DepartmentFeignClient {

    @Override
    public Department findByCode(String code, String companyId) {
        //log.info("熔断保护");
        return null;
    }
}
