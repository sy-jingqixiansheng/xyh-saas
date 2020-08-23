package com.xyh.system.service;

import com.xyh.common.entity.PageVO;
import cpm.xyh.entity.system.Role;
import cpm.xyh.entity.system.vo.RolePageReqVO;
import cpm.xyh.entity.system.vo.RolePermissionVO;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 15:40
 * @Version 1.0
 */
public interface RoleService {
    void saveRole(Role role);

    void update(Role role);

    void delete(String id);

    Role findById(String id);

    PageVO<Role> findPageRole(RolePageReqVO vo);

    /**
     * 分配权限
     */
    void assignPerms(RolePermissionVO rolePermissionVor);

    List<Role> findRoles();
}
