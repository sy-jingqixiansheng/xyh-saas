package com.xyh.system;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**   JWT 认证的方式
 * 把写好的拦截器JwtInterceptor进行注册
 */
//@Configuration
//public class SystemConfig extends WebMvcConfigurationSupport {
//
//    @Autowired
//    private JwtInterceptor jwtInterceptor;
//
//    /**
//     * 添加拦截器的配置
//     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        //1.添加自定义拦截器
//        registry.addInterceptor(jwtInterceptor).
//                addPathPatterns("/**").//2.指定拦截器的url地址
//                excludePathPatterns("/sys/login","/frame/register/**");//3.指定不拦截的url地址
//    }
//}
