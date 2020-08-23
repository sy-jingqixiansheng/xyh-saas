package com.xyh.system.controller;

import com.xyh.common.controller.BaseController;
import com.xyh.common.entity.PageVO;
import com.xyh.common.entity.Result;
import com.xyh.common.entity.ResultCode;
import com.xyh.system.service.RoleService;
import cpm.xyh.entity.system.Role;
import cpm.xyh.entity.system.vo.RolePageReqVO;
import cpm.xyh.entity.system.vo.RolePermissionVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 15:34
 * @Version 1.0
 */

//@CrossOrigin
@RestController
@RequestMapping("/sys")
public class RoleComtroller extends BaseController {

    @Autowired
    private RoleService roleService;
    //添加角色
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public Result add(@RequestBody Role role) {
        String companyId = "1";
        role.setCompanyId(companyId);
        roleService.saveRole(role);
        return Result.SUCCESS();
    }

    //更新角色
    @PutMapping("/updateRole/{id}")
    public Result update(@PathVariable(name = "id") String id, @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);
        return Result.SUCCESS();
    }
    //删除角色
    @RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        roleService.delete(id);
        return Result.SUCCESS();
    }
    /**
     * 根据ID获取角色信息
     */
    @RequestMapping(value = "/findRoleById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Role role = roleService.findById(id);
        return new Result(ResultCode.SUCCESS,role);
    }

    //分页查询角色信息
    @ApiOperation(value = "分页获取角色列表接口")
    @PostMapping("/findRoleByPage")
    public Result findByPage(@RequestBody RolePageReqVO vo){
        vo.setCompanyId(companyId);
        PageVO<Role> pageVO = roleService.findPageRole(vo);
        return new Result(ResultCode.SUCCESS,pageVO);
    }


    //查询全部角色
    @GetMapping("/role/list")
    public Result findRoles(){
        List<Role> roles= roleService.findRoles();
        return new Result(ResultCode.SUCCESS,roles);
    }

    /**
     * 分配权限
     */
    @RequestMapping(value = "/role/assignPrem", method = RequestMethod.PUT)
    public Result assignPrem(@RequestBody RolePermissionVO rolePermissionVor) {

        roleService.assignPerms(rolePermissionVor);
        return new Result(ResultCode.SUCCESS);
    }
}
