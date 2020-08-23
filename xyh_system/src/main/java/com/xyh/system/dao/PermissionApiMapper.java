package com.xyh.system.dao;


import cpm.xyh.entity.system.PermissionApi;
import org.springframework.stereotype.Repository;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 18:02
 * @Version 1.0
 */
@Repository
public interface PermissionApiMapper {
    void save(PermissionApi api);

    void update(PermissionApi api);

    Object findById(String id);

    void deleteById(String id);
}
