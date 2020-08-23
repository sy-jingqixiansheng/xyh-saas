package com.xyh.system.service.Impl;

import com.github.pagehelper.PageHelper;
import com.xyh.common.entity.PageUtil;
import com.xyh.common.entity.PageVO;
import com.xyh.common.entity.ResultCode;
import com.xyh.common.exception.CommonException;
import com.xyh.common.utils.IdWorker;
import com.xyh.system.dao.PermissionMapper;
import com.xyh.system.dao.RoleMapper;
import com.xyh.system.dao.RolePermissionMapper;

import com.xyh.system.service.RoleService;
import cpm.xyh.entity.system.Permission;
import cpm.xyh.entity.system.Role;
import cpm.xyh.entity.system.vo.RolePageReqVO;
import cpm.xyh.entity.system.vo.RolePermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 15:41
 * @Version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void saveRole(Role role) {
        //填充其他参数
        role.setId(idWorker.nextId() + "");
        roleDao.saveRole(role);
    }

    @Override
    public void update(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void delete(String id) {
        roleDao.deleteRole(id);
    }

    @Override
    public Role findById(String id) {
        List<String> perIds = rolePermissionMapper.findByRoleId(id);
        Role role = roleDao.findRoleById(id);
        if(role!=null){
            role.setPermIds(perIds);
            return role;
        }else {
            throw new CommonException(ResultCode.FAIL);
        }
    }

    @Override
    public PageVO<Role> findPageRole(RolePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Role> userPage= roleDao.findPageRole(vo);
        return PageUtil.getPageVO(userPage);
    }


    /**
     * 给角色分配权限
     * @param rolePermissionVor
     */
    @Override
    public void assignPerms(RolePermissionVO rolePermissionVor) {
        String roleId = rolePermissionVor.getId();
        List<String> permIds = rolePermissionVor.getPermIds();
        //传的值为空就是根据角色删除权限
        if(permIds.isEmpty()){
            rolePermissionMapper.deleteRolePer(roleId);
        }else {
            //根据用户id查询数据库已有的角色id集合,与传过来的比较
            List<String> permission = rolePermissionMapper.findByRoleId(roleId);
            if (permission.isEmpty()) {
                rolePermissionMapper.assignPerms(roleId,permIds);
            }
            if(!permission.isEmpty() | permIds.size() >= permission.size()){
                rolePermissionMapper.assignPerms(roleId,permIds);
            }
            if(permIds.size() < permission.size()) {
                //删除少的
                ArrayList<String> arrayList = new ArrayList<>();
                List<Permission> perList = permissionMapper.findByIds(permIds);
                if(perList!=null){
                    for(Permission per:perList){
                        if(per.getPid().length() > 1){
                            arrayList.add(per.getPid());
                        }
                    }
                    //list集合去重复
                    arrayList.stream().distinct().collect(Collectors.toList());
                    permIds.removeAll(arrayList);
                    permIds.addAll(arrayList);
                    permission.removeAll(permIds);
                    rolePermissionMapper.deletePerIds(permission);
                }

            }
        }


    }

    @Override
    public List<Role> findRoles() {
        return roleDao.findRoles();
    }


}
