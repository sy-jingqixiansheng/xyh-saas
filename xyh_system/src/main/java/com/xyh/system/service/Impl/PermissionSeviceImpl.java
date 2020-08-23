package com.xyh.system.service.Impl;

import com.xyh.common.entity.ResultCode;
import com.xyh.common.exception.CommonException;
import com.xyh.common.utils.BeanMapUtils;
import com.xyh.common.utils.IdWorker;
import com.xyh.common.utils.PermissionConstants;
import com.xyh.system.dao.PermissionApiMapper;
import com.xyh.system.dao.PermissionMapper;
import com.xyh.system.dao.PermissionMenuMapper;
import com.xyh.system.dao.PermissionPointMapper;
import com.xyh.system.service.PermissionService;
import cpm.xyh.entity.system.Permission;
import cpm.xyh.entity.system.PermissionApi;
import cpm.xyh.entity.system.PermissionMenu;
import cpm.xyh.entity.system.PermissionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * @Author SQ
 * @Date 2020/8/12 0012 17:12
 * @Version 1.0
 */
@Service
@Transactional
public class PermissionSeviceImpl implements PermissionService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PermissionApiMapper permissionApiMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PermissionPointMapper permissionPointMapper;
    @Autowired
    private PermissionMenuMapper permissionMenuMapper;


    @Override
    public void save(Map<String, Object> map) throws Exception {
        String id = idWorker.nextId() + "";
        //1.通过map构造permission对象
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        perm.setId(id);
        //2.根据类型构造不同的资源对象（菜单，按钮，api）
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                //调用工具类把map转成对象
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(id);
                permissionMenuMapper.save(menu);
                break;
            case PermissionConstants.PERMISSION_POINT:
                //调用工具类把map转成对象
                PermissionPoint point =
                        BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(id);
                permissionPointMapper.save(point);
                break;
            case PermissionConstants.PERMISSION_API:
                //调用工具类把map转成对象
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(id);
                permissionApiMapper.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //3.保存
        permissionMapper.save(perm);
    }

    @Override
    public void update(Map<String, Object> map) throws Exception {
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        //1.通过传递的权限id查询权限
        Permission permission = permissionMapper.findById(perm.getId());
        permission.setName(perm.getName());
        permission.setCode(perm.getCode());
        permission.setDescription(perm.getDescription());
        permission.setEnVisible(perm.getEnVisible());
        //2.根据类型构造不同的资源
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(perm.getId());
                permissionMenuMapper.update(menu);
                break;
            case PermissionConstants.PERMISSION_POINT:
                PermissionPoint point =
                        BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(perm.getId());
                permissionPointMapper.update(point);
                break;
            case PermissionConstants.PERMISSION_API:
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(perm.getId());
                permissionApiMapper.update(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //3.保存
        permissionMapper.update(permission);
    }

    /**
     * 查询全部
     * type : type：0：菜单 + 按钮（权限点） 1：菜单2：按钮（权限点）3：API接
     * <p>
     * enVisible : 0：查询所有saas平台的最高权限，1：查询企业的权限
     * pid ：父id
     */
    @Override
    public List<Permission> findAll(Map<String, Object> map) {
        if(map==null){
            return permissionMapper.findPermission();
        }else {
            return permissionMapper.findAll(map);
        }
    }

    /**
     * 根据id查询
     * //1.查询权限
     * //2.根据权限的类型查询资源
     * //3.构造map集合
     */
    @Override
    public Map<String, Object> findById(String id) {
        Permission perm = permissionMapper.findById(id);
        int type = perm.getType();
        Object object = null;
        if (type == PermissionConstants.PERMISSION_MENU) {
            object = permissionMenuMapper.findById(id);
        } else if (type == PermissionConstants.PERMISSION_POINT) {
            object = permissionPointMapper.findById(id);
        } else if (type == PermissionConstants.PERMISSION_API) {
            object = permissionApiMapper.findById(id);
        } else {
            throw new CommonException(ResultCode.FAIL);
        }
        Map<String, Object> map = BeanMapUtils.beanToMap(object);
        map.put("name", perm.getName());
        map.put("type", perm.getType());
        map.put("code", perm.getCode());
        map.put("description", perm.getDescription());
        map.put("pid", perm.getPid());
        map.put("enVisible", perm.getEnVisible());
        return map;
    }

    /**
     * 根据id删除
     * //1.删除权限
     * //2.删除权限对应的资源
     */
    @Override
    public void deleteById(String id) {
        //1.通过传递的权限id查询权限
        Permission permission = permissionMapper.findById(id);
        permissionMapper.delete(permission);
        //2.根据类型构造不同的资源
        int type = permission.getType();
        switch (type) {
            case PermissionConstants.PERMISSION_MENU:
                permissionMenuMapper.deleteById(id);
                break;
            case PermissionConstants.PERMISSION_POINT:
                permissionPointMapper.deleteById(id);
                break;
            case PermissionConstants.PERMISSION_API:
                permissionApiMapper.deleteById(id);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
    }

}

