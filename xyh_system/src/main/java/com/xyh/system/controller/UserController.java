package com.xyh.system.controller;


import com.github.pagehelper.PageInfo;
import com.xyh.common.controller.BaseController;
import com.xyh.common.entity.PageResult;
import com.xyh.common.entity.Result;
import com.xyh.common.entity.ResultCode;
import com.xyh.common.entity.resqonse.ProfileResultDTO;
import com.xyh.common.exception.CommonException;
import com.xyh.common.poi.ExcelImportUtil;
import com.xyh.system.client.DepartmentFeignClient;
import com.xyh.system.dao.PermissionMapper;
import com.xyh.system.dao.RolePermissionMapper;
import com.xyh.system.dao.UserRoleMapper;
import com.xyh.system.service.PermissionService;
import com.xyh.system.service.UserService;
import cpm.xyh.entity.system.Permission;
import cpm.xyh.entity.system.User;
import cpm.xyh.entity.system.vo.UserPageReqVO;
import cpm.xyh.entity.system.vo.UserRoleVo;
import cpm.xyh.entity.system.vo.UserSimpleResult;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 8:51
 * @Version 1.0
 */
//@CrossOrigin
@RestController
@RequestMapping("/sys")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;



    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");

        //Shiro登录方式
        try {
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码
            password = new Md5Hash(password,mobile,3).toString(); //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile,password);
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String)subject.getSession().getId();
            //5.构造返回结果
            return new Result(ResultCode.SUCCESS,sessionId);
        }catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR,e.getMessage());
        }
        //JTW 登录方式
//        User user = userService.findByMobile(mobile);
//        //登录失败
//        if(user == null || !user.getPassword().equals(password)) {
//            return new Result(ResultCode.MOBILEORPASSWORDERROR);
//        }else {
//            //登录成功
//            //获取所有可访问API的权限
//            StringBuilder stringBuilder = new StringBuilder();
//            List<String> roleIds = userRoleMapper.byUserIdRoleIds(user.getId());
//            List<String> perIds = rolePermissionMapper.findByRoleIdList(roleIds);
//            List<Permission> perList = permissionMapper.findByIds(perIds);
//            for(Permission per: perList){
//                if(per.getType() == PermissionConstants.PERMISSION_API){
//                    stringBuilder.append(per.getCode()).append(",");
//                }
//            }
//            Map<String,Object> map = new HashMap<>();
//            map.put("apis",stringBuilder.toString());//可访问api权限的字符串
//            map.put("companyId",user.getCompanyId());
//            map.put("companyName",user.getCompanyName());
//            String token = JwtUtil.createJWT(user.getId(), user.getUsername(), map);
//            return new Result(ResultCode.SUCCESS,token);
//        }
    }

    /**
     * 登录成功后获取个人信息
     */
    @PostMapping(value = "/profile")
    public Result profile(HttpServletRequest request) throws Exception{
        //Shiro方式从session中获取安全数据
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        if(principals==null){
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }

        ProfileResultDTO resultDTO = (ProfileResultDTO) principals.getPrimaryPrincipal();


        //JTW 本地数据库获取方式
//        Claims claims = (Claims) request.getAttribute("user_claims");
//        if(claims == null) {
//            throw new CommonException(ResultCode.UNAUTHENTICATED);
//        }
//        String userId = claims.getId();
//
//        //获取用户信息
//        User user = userService.findById(userId);
//
//        /**
//         *  saasAdmin: 平台管理员   拥有所有权限
//         *  coAdmin: 企业管理者     拥有企业权限
//         *  user: 普通用户(需要分配角色)  拥有当前角色权限
//         */
//        ProfileResultDTO proDTO = new ProfileResultDTO();
//
//        if("saasAdmin".equals(user.getLevel())){
//            List<Permission> perList = permissionMapper.findPermission();
//            List<String> perIdLsit = new ArrayList<>();
//            for(Permission per :perList){
//                perIdLsit.add(per.getId());
//            }
//            //根据权限id集合查找权限
//            Map PerAPM = findMFAByPerIds(perIdLsit);
//            proDTO.setRoles(PerAPM);
//        }else if("coAdmin".equals(user.getLevel())){
//            Map<String,Object> map = new HashMap<>();
//            map.put("enVisible","1");
//            List<Permission> permissionList = permissionService.findAll(map);
//            List<String> perIdLsits = new ArrayList<>();
//            for(Permission per :permissionList){
//                perIdLsits.add(per.getId());
//            }
//            //根据权限id集合查找权限
//            Map PerAPM = findMFAByPerIds(perIdLsits);
//            proDTO.setRoles(PerAPM);
//        }else if("user".equals(user.getLevel())){
//            proDTO.setMobile(user.getMobile());
//            proDTO.setUsername(user.getUsername());
//            proDTO.setCompany(user.getCompanyName());
//
//            //根据用户id查找角色id集合
//            List<String> roles =  userRoleMapper.byUserIdRoleIds(user.getId());
//            if(roles.isEmpty()){
//                throw new CommonException(ResultCode.NOTNULROLES);
//            }
//            //根据角色id集合查找权限
//            List<String> permssionsList = rolePermissionMapper.findByRoleIdList(roles);
//            if(roles.isEmpty()){
//                throw new CommonException(ResultCode.NOTNULPERMISSION);
//            }
//            //根据权限id集合查找权限
//            Map PerAPM = findMFAByPerIds(permssionsList);
//            proDTO.setRoles(PerAPM);
//        }
        return new Result(ResultCode.SUCCESS,resultDTO);
    }

    //根据权限id集合查找权限
    private Map findMFAByPerIds(List<String> permssList){
        //根据权限id集合查找权限
        List<Permission> permissions = permissionMapper.findByIds(permssList);
        HashSet<String> menus = new HashSet<>();
        HashSet<String> points = new HashSet<>();
        HashSet<String> apis = new HashSet<>();
        Map rolesMap = new HashMap<>();
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

    //保存用户
    @PostMapping("/saveUser")
    public Result SaceUser(@RequestBody User user) {
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        userService.save(user);
        return Result.SUCCESS();
    }

    //根据id查询用户
    @GetMapping("/findByIdUser/{id}")
    public Result findById(@PathVariable String id) {
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS, user);
    }

    //根据id删除用户
    @RequiresPermissions("API-USER-DELET")
    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.DELETE,name = "API-USER-DELETE")
    public Result deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return Result.SUCCESS();
    }

    //根据id更新用户
    @PutMapping("/updateUser/{id}")
    public Result updateUser(@PathVariable String id,
                             @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Result.SUCCESS();
    }

    //分页条件查询用户
    @ApiOperation(value = "分页获取用户列表接口")
    @PostMapping("/findUserByPage")
    public Result findByPage(@RequestBody UserPageReqVO vo) {
        vo.setCompanyId(companyId);
        PageInfo<User> pageInfo = userService.findPageUser(vo);
        PageResult<User> pr = new PageResult<>();
        pr.setRows(pageInfo.getList());
        pr.setTotal(pageInfo.getTotal());
        return new Result(ResultCode.SUCCESS, pr);
    }

    @RequestMapping(value = "/user/simple", method = RequestMethod.GET)
    public Result simple() throws Exception {
        List<UserSimpleResult> list = new ArrayList<>();
        List<User> users = userService.findAll(companyId);
        for (User user : users) {
            list.add(new UserSimpleResult(user.getId(),user.getUsername()));
        }
        return new Result(ResultCode.SUCCESS,list);
    }
    //给用户分配角色
    @PutMapping("/user/assignRoles")
    public Result assignRoles(@RequestBody UserRoleVo userRoleVo) {
//        //1.获取被分配的用户id
//        String userId = (String) map.get("id");
//        //2.获取到角色的id列表
//        List<String> roleIds = (List<String>) map.get("roleIds");
//        //3.调用service完成角色分配
//        userService.assignRoles(userId, roleIds);
        userService.assignRoles(userRoleVo);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 导入Excel，添加用户
     *  文件上传：springboot
     */
    @RequestMapping(value="/user/import",method = RequestMethod.POST)
    public Result importUser(@RequestParam(name="file") MultipartFile file) throws Exception {
        /**
         * EasyExcle操作
         */
        User user = new User();
        user.setCompanyName(companyName);
        user.setCompanyId(companyId);
        try {
            InputStream inputStream = file.getInputStream();
            userService.batchImport(inputStream,user);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            throw new CommonException(ResultCode.FAIL);
        }



        /**
         * POI 操作
         */
//        //根据Excel文件创建工作簿
//        Workbook wb = new XSSFWorkbook(file.getInputStream());
//        //获取Sheet
//        Sheet sheet = wb.getSheetAt(0);//参数：索引
//        //获取Sheet中的每一行，和每一个单元格
//        //获取用户数据列表
//        List<User> list = new ArrayList<>();
//        System.out.println(sheet.getLastRowNum());
//        for (int rowNum = 1; rowNum<= sheet.getLastRowNum() ;rowNum ++) {
//            Row row = sheet.getRow(rowNum);//根据索引获取每一个行
//            Object [] values = new Object[row.getLastCellNum()];
//            for(int cellNum=1;cellNum< row.getLastCellNum(); cellNum ++) {
//                Cell cell = row.getCell(cellNum);
//                Object value = getCellValue(cell);
//                values[cellNum] = value;
//            }
//            User user = new User(values);
//            list.add(user);
//        }

        //通过工具类完成 **要对User进行自定义注解配置
//      List<User> list = new ExcelImportUtil(User.class).readExcel(file.getInputStream(), 1, 1);
        //批量保存用户  谁操作就是哪个公司的
//        userService.saveAll(list,companyId,companyName);//BaseController中取值
//
//        return new Result(ResultCode.SUCCESS);
    }
//    /**
//     * 导入Excel，添加用户
//     *  文件上传：springboot
//     */
//    public static Object getCellValue(Cell cell) {
//        //1.获取到单元格的属性类型
//        CellType cellType = cell.getCellType();
//        //2.根据单元格数据类型获取数据
//        Object value = null;
//        switch (cellType) {
//            case STRING:
//                value = cell.getStringCellValue();
//                break;
//            case BOOLEAN:
//                value = cell.getBooleanCellValue();
//                break;
//            case NUMERIC:
//                if(DateUtil.isCellDateFormatted(cell)) {
//                    //日期格式
//                    value = cell.getDateCellValue();
//                }else{
//                    //数字
//                    value = cell.getNumericCellValue();
//                }
//                break;
//            case FORMULA: //公式
//                value = cell.getCellFormula();
//                break;
//            default:
//                break;
//        }
//        return value;
//    }

    /**
     * 保存头像图片
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/upload/{id}")
    public Result upload(@PathVariable String id,@RequestParam(name="file") MultipartFile file ) throws IOException {
        //1.调用service保存图片（获取到图片的访问地址（dataUrl | http地址））
        String imgUrl = userService.uploadImage(id,file);
        //2.返回数据
        return new Result(ResultCode.SUCCESS,imgUrl);
    }
}
