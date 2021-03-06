package cpm.xyh.entity.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Company implements Serializable {
    //ID
    private String id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 企业登录账号ID
     */
    private String managerId;
    /**
     * 当前版本
     */
    private String version;
    /**
     * 续期时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date renewalDate;
    /**
     * 到期时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expirationDate;
    /**
     * 公司地区
     */
    private String companyArea;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 营业执照-图片ID
     */
    private String businessLicenseId;
    /**
     * 法人代表
     */
    private String legalRepresentative;
    /**
     * 公司电话
     */
    private String companyPhone;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 公司规模
     */
    private String companySize;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 审核状态
     */
    private String auditState;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 当前余额
     */
    private double balance;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;
}
