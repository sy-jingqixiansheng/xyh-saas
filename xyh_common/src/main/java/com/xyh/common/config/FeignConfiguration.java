package com.xyh.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author SQ  置Feign拦截器添加请求头  解决微服务间调用
 * @Date 2020/8/17 0017 17:32
 * @Version 1.0
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            //获取所有浏览器发送的请求属性，请求头赋值到feign
            @Override
            public void apply(RequestTemplate template) {
                ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    //获取浏览器发起的请求头
                    HttpServletRequest request = attributes.getRequest();
                    Enumeration<String> headerNames = request.getHeaderNames();
                    if (headerNames != null) {
                        while (headerNames.hasMoreElements()) {
                            String name = headerNames.nextElement(); //请求头名称 Authorization
                            String values = request.getHeader(name);//请求头数据 "Bearer b1dbb4cf-7de6-41e5-99e2-0e8b7e8fe6ee"
                            template.header(name, values);
                        }
                    }
                }
            }
        };
    }
}