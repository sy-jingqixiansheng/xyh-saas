package com.xyh.system.dao;

import cpm.xyh.entity.system.PermissionPoint;
import org.springframework.stereotype.Repository;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 18:02
 * @Version 1.0
 */
@Repository
public interface PermissionPointMapper {
    void save(PermissionPoint point);

    void update(PermissionPoint point);

    Object findById(String id);

    void deleteById(String id);
}
