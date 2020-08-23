package com.xyh.system.shiro.realm;


import com.xyh.common.entity.ResultCode;
import com.xyh.common.entity.resqonse.ProfileResultDTO;
import com.xyh.common.exception.CommonException;
import com.xyh.common.shiro.realm.XyhRealm;
import com.xyh.system.dao.PermissionMapper;
import com.xyh.system.dao.RolePermissionMapper;
import com.xyh.system.dao.UserRoleMapper;
import com.xyh.system.service.PermissionService;
import com.xyh.system.service.UserService;
import cpm.xyh.entity.system.Permission;
import cpm.xyh.entity.system.User;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserRealm extends XyhRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;
    
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String( upToken.getPassword());
        //2.根据手机号查询用户
        User user = userService.findByMobile(mobile);
        //3.判断用户是否存在，用户密码是否和输入密码一致
        if(user != null && user.getPassword().equals(password)) {
            ProfileResultDTO resultDTO = new ProfileResultDTO();
            //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
            resultDTO = findUserPermissionByUserId(user);
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(resultDTO,user.getPassword(),this.getName());
            return info;
        }
        //返回null，会抛出异常，标识用户名和密码不匹配
        return null;
    }

    /**
     * 辅助方法  根据用户id查询用户权限
     * @param user
     * @return
     */
    private ProfileResultDTO findUserPermissionByUserId(User user) {
        ProfileResultDTO proDTO = new ProfileResultDTO();
        if("saasAdmin" .equals(user.getLevel())){
            proDTO.setMobile(user.getMobile());
            proDTO.setUserId(user.getId());
            proDTO.setUsername(user.getUsername());
            proDTO.setCompanyId(user.getCompanyId());
            proDTO.setCompany(user.getCompanyName());
            Map<String, Object> map = new HashMap<>();
            map.put("enVisible", "1");
            List<Permission> permissionList = permissionMapper.findPermission();
            List<String> perIdLsits = new ArrayList<>();
            for (Permission per : permissionList) {
                perIdLsits.add(per.getId());
            }
            //根据权限id集合查找权限
            Map PerAPM = findMFAByPerIds(perIdLsits);
            proDTO.setRoles(PerAPM);
        }else if ("coAdmin".equals(user.getLevel())) {
            proDTO.setMobile(user.getMobile());
            proDTO.setUserId(user.getId());
            proDTO.setUsername(user.getUsername());
            proDTO.setCompanyId(user.getCompanyId());
            proDTO.setCompany(user.getCompanyName());
            Map<String, Object> map = new HashMap<>();
            map.put("enVisible", "1");
            List<Permission> permissionList = permissionService.findAll(map);
            List<String> perIdLsits = new ArrayList<>();
            for (Permission per : permissionList) {
                perIdLsits.add(per.getId());
            }
            //根据权限id集合查找权限
            Map PerAPM = findMFAByPerIds(perIdLsits);
            proDTO.setRoles(PerAPM);
        } else {
            if ("user".equals(user.getLevel())) {
            proDTO.setMobile(user.getMobile());
            proDTO.setUserId(user.getId());
            proDTO.setUsername(user.getUsername());
            proDTO.setCompanyId(user.getCompanyId());
            proDTO.setCompany(user.getCompanyName());

            //根据用户id查找角色id集合
            List<String> roles = userRoleMapper.byUserIdRoleIds(user.getId());
            if (roles.isEmpty()) {
                throw new CommonException(ResultCode.NOTNULROLES);
            }
            //根据角色id集合查找权限
            List<String> permssionsList = rolePermissionMapper.findByRoleIdList(roles);
            if (roles.isEmpty()) {
                throw new CommonException(ResultCode.NOTNULPERMISSION);
            }
            //根据权限id集合查找权限
                Map mfaByPerIds = findMFAByPerIds(permssionsList);
                proDTO.setRoles(mfaByPerIds);
        }
        }
        return proDTO;
    }

        //根据权限id集合查找权限
        private Map findMFAByPerIds(List<String> permssList){
            //根据权限id集合查找权限
            List<Permission> permissions = permissionMapper.findByIds(permssList);
            HashSet<String> menus = new HashSet<>();
            HashSet<String> points = new HashSet<>();
            HashSet<String> apis = new HashSet<>();
            HashMap<String, Object> rolesMap = new HashMap<>();
            for (Permission perm :permissions) {
                String code = perm.getCode();
                if(perm.getType() == 1) {
                    menus.add(code);
                }else if(perm.getType() == 2) {
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
            rolesMap.put("menus",menus);
            rolesMap.put("points",points);
            rolesMap.put("apis",points);
            return rolesMap;
        }
}
