package com.xyh.system.client;

/**
 * @Author SQ  服务调用端
 * @Date 2020/8/17 0017 17:13
 * @Version 1.0
 */


import com.xyh.system.client.fallback.DepartmentFeignClientFallBack;
import cpm.xyh.entity.company.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//FeignClient注解用于指定从哪个服务中调用功能 ，注意里面的名称与被调用的服务名保持一致
//@Component注解防止，在其他位置注入CodClient时idea报错
//@PathVariable注解一定要指定参数名称，否则出错
@Component
@FeignClient(value = "xyh-company",fallback = DepartmentFeignClientFallBack.class)//容错处理指定类
public interface DepartmentFeignClient {

    /**
     *服务调用(根据部门code,companyId查询部门)
     */
    @RequestMapping(value = "/company/department/search", method = RequestMethod.POST)
    Department findByCode(@RequestParam("code") String code, @RequestParam("companyId") String companyId);

}