package com.xyh.system.service;



import cpm.xyh.entity.system.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 17:12
 * @Version 1.0
 */
public interface PermissionService {

    void save(Map<String, Object> map) throws Exception;

    void update(Map<String, Object> map) throws Exception;

    List<Permission> findAll(Map<String,Object>map);

    Map findById(String id);



    void deleteById(String id);
}
