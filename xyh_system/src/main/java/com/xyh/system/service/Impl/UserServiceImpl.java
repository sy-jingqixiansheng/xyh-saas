package com.xyh.system.service.Impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyh.common.entity.ResultCode;
import com.xyh.common.exception.CommonException;
import com.xyh.common.utils.IdWorker;
import com.xyh.common.utils.QiniuUploadUtil;
import com.xyh.system.client.DepartmentFeignClient;
import com.xyh.system.dao.UserMapper;
import com.xyh.system.dao.UserRoleMapper;
import com.xyh.system.excel.ExcelUserListener;
import com.xyh.system.service.UserService;
import cpm.xyh.entity.company.Department;
import cpm.xyh.entity.system.User;
import cpm.xyh.entity.system.vo.UserPageReqVO;
import cpm.xyh.entity.system.vo.UserRoleVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;



/**
 * @Author SQ
 * @Date 2020/8/12 0012 8:55
 * @Version 1.0
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

//    public static void main(String[] args) {
//        String password = new Md5Hash("123456", "13555850751", 3).toString();
//        System.out.println(password);
//    }

    //保存用户
    @Override
    public void save(User user) {
        //填充其他参数
        user.setId(idWorker.nextId() + "");
        user.setCreateTime(new Date()); //创建时间
        //对密码进行加密
        String password = new Md5Hash(user.getPassword(), user.getMobile(), 3).toString();
        user.setLevel("user");//设置用户等级
        user.setPassword(password);//设置默认登录密码
        user.setEnableState(1);//状态
        userMapper.saveUser(user);
    }

    //根据id查询用户
    @Override
    public User findById(String id) {
        List<String> roleIdList =userRoleMapper.byUserIdRoleIds(id);
        User idUser = userMapper.findByIdUser(id);
        if(idUser!=null){
            idUser.setRoleIds(roleIdList);
            return idUser;
        }else {
            throw new CommonException(ResultCode.FAIL);
        }


    }

    //根据id删除用户
    @Override
    public void deleteUser(String id) {
        userMapper.deleteById(id);
    }

    //根据id更新用户
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    //分页查询用户
    @Override
    public PageInfo<User> findPageUser(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPage(),vo.getSize());
        List<User> userPage= userMapper.findPageUser(vo);
        return new PageInfo<User>(userPage);
    }

    //给用户分配角色
    @Override
    public void assignRoles(UserRoleVo userRoleVo) {
        String userId = userRoleVo.getId();
        List<String> roleIds = userRoleVo.getRoleIds();
        if(roleIds.isEmpty() ){
            userRoleMapper.deleteUserRole(userId);
        }else {
            //根据用户id查询数据库已有的角色id集合,与传过来的比较
            List<String> oldRoleIds = userRoleMapper.byUserIdRoleIds(userId);
            if (oldRoleIds.isEmpty()) {
                userRoleMapper.saveUserRole(userId,roleIds);
            }if(!oldRoleIds.isEmpty() | roleIds.size() >= oldRoleIds.size()){
                userRoleMapper.saveUserRole(userId,roleIds);
            }
            if(roleIds.size() < oldRoleIds.size()){
                //删除少的
                oldRoleIds.removeAll(roleIds);
                userRoleMapper.deleteRoleIds(oldRoleIds);
            }
        }

    }

    //根据手机号查询用户
    @Override
    public User findByMobile(String mobile) {
        return userMapper.findUserByPhone(mobile);
    }

    //批量保存用户
    @Override
    public void saveAll(List<User> list, String companyId, String companyName) {
        for (User user : list) {
            //配置默认密码
            user.setPassword(new Md5Hash("123456", user.getMobile(), 3).toString());
            //配置id
            user.setId(idWorker.nextId() + "");
            //其他基本属性
            user.setInServiceStatus(1);
            user.setCompanyId(companyId);
            user.setCompanyName(companyName);
            user.setEnableState(1);
            user.setLevel("user"); //用户级别

            //获取部门信息
            Department dept = departmentFeignClient.findByCode(user.getDepartmentId(), user.getCompanyId());
            if (dept != null) {
                user.setDepartmentId(dept.getId());
                user.setDepartmentName(dept.getName());
            }
            userMapper.saveUser(user);
        }

    }

    @Override
    public List<User> findAll(String companyId) {
        return userMapper.findAll(companyId);
    }

    /**
     * 头像上传
     * @param id
     * @param file
     * @return
     */
    @Override
    public String uploadImage(String id, MultipartFile file) throws IOException {
        /**
         * 上传到七牛云存储
         */
        //根据id查询
        User user = userMapper.findByIdUser(id);
        String key = new QiniuUploadUtil().upload(user.getId(), file.getBytes());
        if(key != null) {
            user.setStaffPhoto(key);
            userMapper.update(user);
        }
        return key;


        /**
         * DataURL方式
         */
        //        //1.根据id查询用户
//        User user = userMapper.findByIdUser(id);
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        //2.使用DataURL的形式存储图片（对图片byte数组进行base64编码）
//        String encode = "data:image/png;base64,"+ base64Encoder.encode(file.getBytes());
//
//        //3.更新用户头像地址
//        user.setStaffPhoto(encode);
//        userMapper.update(user);
//        //4.返回
//        return encode;
    }


//    // 对字节数组字符串进行Base64解码并生成图片
//    public static boolean GenerateImage(String imgStr, String imgFilePath) {
//        if (imgStr == null) // 图像数据为空
//            return false;
//        System.out.println("照片："+imgStr);
//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            // Base64解码
//            byte[] bytes = decoder.decodeBuffer(imgStr);
//            for (int i = 0; i < bytes.length; ++i) {
//                if (bytes[i] < 0) {// 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
//            // 生成jpeg图片
//            OutputStream out = new FileOutputStream(imgFilePath);
//            out.write(bytes);
//            out.flush();
//            out.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchImport(InputStream inputStream,User user) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ExcelReaderBuilder read = EasyExcel.read(inputStream, User.class, new ExcelUserListener(userMapper,user,idWorker,departmentFeignClient));
        read.excelType(ExcelTypeEnum.XLSX);
        ExcelReaderSheetBuilder sheet = read.sheet();
        sheet.doRead();
    }

}
