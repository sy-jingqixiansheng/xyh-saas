package com.xyh.system.dao;


import cpm.xyh.entity.system.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 17:13
 * @Version 1.0
 */
@Repository
public interface PermissionMapper {
    void save(Permission perm);

    Permission findById(String id);

    void update(Permission permission);

    List<Permission> findAll(@Param("hashMapQuery") Map<String,Object> map);

    void delete(Permission permission);

    List<Permission> findByIds(@Param("perListIds") List<String> permssionsList);

    List<Permission> findPermission();
}
