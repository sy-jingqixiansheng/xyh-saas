package com.xyh.system.service;

import com.github.pagehelper.PageInfo;
import cpm.xyh.entity.system.User;
import cpm.xyh.entity.system.vo.UserPageReqVO;
import cpm.xyh.entity.system.vo.UserRoleVo;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 8:54
 * @Version 1.0
 */
public interface UserService {

    //保存用户
    void save(User user);

    //根据id查询用户
    User findById(String id);
    //根据id删除用户
    void deleteUser(String id);
    //根据id更新用户
    void update(User user);

    //分页查询用户
    PageInfo<User> findPageUser(UserPageReqVO vo);
    //给用户分配角色
    void assignRoles(UserRoleVo userRoleVo);

    User findByMobile(String mobile);

    //批量保存用户
    void saveAll(List<User> list, String companyId, String companyName);

    List<User> findAll(String companyId);

    String uploadImage(String id, MultipartFile file) throws IOException;

    void batchImport(InputStream inputStream,User user);
}
