package cpm.xyh.entity.system;


import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户实体类
 */

@Data
public class User implements Serializable {

    private String id;
    /**
     * 手机号码
     */
    //@ExcelAttribute(sort = 2)
    @ExcelProperty(index = 1)
    private String mobile;
    /**
     * 用户名称
     */
    //@ExcelAttribute(sort = 1)
    @ExcelProperty(index = 0)
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态 0为禁用 1为启用
     */
    private Integer enableState;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private String companyId;

    private String companyName;

    /**
     * 部门ID
     */
    //@ExcelAttribute(sort = 6)
    @ExcelProperty(index = 5)
    private String departmentId;

    /**
     * 入职时间
     */
   // @ExcelAttribute(sort = 5)
    @ExcelProperty(index = 4)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    //@ExcelAttribute(sort = 4)
    @ExcelProperty(index = 3)
    private Integer formOfEmployment;

    /**
     * 工号
     */
    //@ExcelAttribute(sort = 3)
    @ExcelProperty(index = 2)
    private String workNumber;

    /**
     * 管理形式
     */
    private String formOfManagement;

    /**
     * 工作城市
     */
    private String workingCity;

    private String role;

    /**
     * 用户头像
     */
    private String staffPhoto;


    /**
     * 转正时间
     */
    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer inServiceStatus;

    private String departmentName;

    /**
     *  saasAdmin: 管理员
     *  coAdmin: 企业管理者
     *  user: 普通用户(需要分配角色)
     */
    private String level;

    private List<String> roleIds;

    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多

    /**
     * 默认构造方法,添加此默认构造方法即可解决 ExecutorException: No constructor found in
     * 创建一个新的实例 MyEntity.
     */
    public User(){
        super();
    }

    //excel导入使用
    public User(Object [] values) {
        //  用户名  手机号 工号  聘用 形式	入职  时间  部门编码
        this.username = values[1].toString();
        this.mobile = values[2].toString();
        this.workNumber = new DecimalFormat("#").format(values[3]).toString();
        this.formOfEmployment =((Double) values[4]).intValue();
        this.timeOfEntry = (Date) values[5];
        this.departmentId = values[6].toString(); //部门编码 != 部门id
    }
}
