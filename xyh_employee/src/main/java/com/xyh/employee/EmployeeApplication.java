package com.xyh.employee;

import com.xyh.common.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author SQ
 * @Date 2020/8/16 0016 21:19
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xyh")
@MapperScan("com.xyh.employee.dao")
@EnableDiscoveryClient
public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
