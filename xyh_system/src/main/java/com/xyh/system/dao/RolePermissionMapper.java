package com.xyh.system.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/13 0013 17:37
 * @Version 1.0
 */
@Repository
public interface RolePermissionMapper {

    void assignPerms(@Param("roleId") String roleId, @Param("permIds") List<String> permIds);

    List<String> findByRoleId(String id);


    void deletePerIds(@Param("permission") List<String> permission);

    void deleteRolePer(String roleId);


    List<String> findByRoleIdList(@Param("roleListIds") List<String> roles);
}
