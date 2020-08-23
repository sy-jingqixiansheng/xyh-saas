package com.xyh.system.dao;
import cpm.xyh.entity.system.Role;
import cpm.xyh.entity.system.vo.RolePageReqVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author SQ
 * @Date 2020/8/12 0012 15:42
 * @Version 1.0
 */
@Repository
public interface RoleMapper {
    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(String id);

    Role findRoleById(String id);

    List<Role> findPageRole(RolePageReqVO vo);

    List<Role> findRoles();
}
