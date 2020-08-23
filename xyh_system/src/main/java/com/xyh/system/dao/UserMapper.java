package com.xyh.system.dao;


import cpm.xyh.entity.system.User;
import cpm.xyh.entity.system.vo.UserPageReqVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 8:57
 * @Version 1.0
 */
@Repository
public interface UserMapper {

    //保存用户
    void saveUser(User user);




    void deleteById(String id);

    void update(User user);

    //分页查询用户
    List<User> findPageUser(UserPageReqVO vo);

    User findByIdUser(String id);

    User findUserByPhone(String mobile);

    List<User> findAll(String companyId);
}
