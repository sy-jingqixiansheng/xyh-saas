package com.xyh.system.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xyh.common.controller.BaseController;
import com.xyh.common.utils.IdWorker;
import com.xyh.system.client.DepartmentFeignClient;
import com.xyh.system.dao.UserMapper;
import cpm.xyh.entity.company.Department;
import cpm.xyh.entity.system.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author SQ  User Excel 监听器
 * @Date 2020/8/19 0019 9:17
 * @Version 1.0
 */

@Slf4j
@AllArgsConstructor //全参
@NoArgsConstructor //无参
public class ExcelUserListener  extends AnalysisEventListener<User> {

    //已经通过传过来的userMapper进行赋值了, 对齐调用操作数据保存到数据库中
    private UserMapper userMapper;
    private User oldUser;
    private IdWorker idWorker;
    private DepartmentFeignClient departmentFeignClient;


    /**
     * 每次读取解析一行内容,都会调用invoke,在invoke操作读取到的数据
     */
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", user);
        user.setId(idWorker.nextId()+"");
        user.setCompanyId(oldUser.getCompanyId());
        user.setCompanyName(oldUser.getCompanyName());
        user.setCreateTime(new Date()); //创建时间
        //对默认密码进行加密
        String password = new Md5Hash("123456", user.getMobile(), 3).toString();
        user.setLevel("user");//设置用户等级
        user.setPassword(password);//设置默认登录密码
        user.setEnableState(1);//状态

        //根据解析的部门code 查询数据库部门id进行保存到user
        Department department = departmentFeignClient.findByCode(user.getDepartmentId(), oldUser.getCompanyId());
        user.setDepartmentId(department.getId());

        userMapper.saveUser(user);
    }

    /**
     * 读取完调用的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
