package com.xyh.system.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/13 0013 15:36
 * @Version 1.0
 */
@Repository
public interface UserRoleMapper {


    void saveUserRole(@Param("id")String userId, @Param("roleIds")List<String> roleIds);


    List<String> byUserIdRoleIds(@Param("userId") String id);

    void deleteRoleIds(@Param("roleIdOrLists") List<String> oldRoleIds);

    void deleteUserRole(String userId);


}
