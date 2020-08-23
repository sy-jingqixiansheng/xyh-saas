package cpm.xyh.entity.employee.response;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import cpm.xyh.entity.employee.EmployeeResignation;
import cpm.xyh.entity.employee.UserCompanyPersonal;
import cpm.xyh.entity.poi.ExcelAttribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeReportResult implements Serializable {

    //自定义注解的使用
    //@ExcelAttribute(sort =0)
    @ExcelProperty(index = 0 ,value = "编号")
    private String userId;
    //@ExcelAttribute(sort =1)
    @ExcelProperty(index = 1 ,value = "用户名")
    private String username;
    @ExcelIgnore
    private String departmentName;
    //@ExcelAttribute(sort =2)
    @ExcelProperty(index = 2,value = "手机号")
    private String mobile;
    //@ExcelAttribute(sort =9)
    @ExcelIgnore
    private String timeOfEntry;
    @ExcelIgnore
    private String companyId;
    @ExcelIgnore
    private String sex;
    /**
     * 出生日期
     */
    @ExcelIgnore
    private String dateOfBirth;
    /**
     * 最高学历
     */
    //@ExcelAttribute(sort = 3)
    @ExcelProperty(index = 4 ,value = "最高学历")
    private String theHighestDegreeOfEducation;
    /**
     * 国家地区
     */
    //@ExcelAttribute(sort =4)
    @ExcelProperty(index = 5 ,value = "国家地区")
    private String nationalArea;
    /**
     * 护照号
     */
    //@ExcelAttribute(sort =6)
    @ExcelProperty(index = 6 ,value = "护照号")
    private String passportNo;
    /**
     * 身份证号
     */
    @ExcelIgnore
    private String idNumber;
    /**
     * 身份证照片-正面
     */
    @ExcelIgnore
    private String idCardPhotoPositive;
    /**
     * 身份证照片-背面
     */
    @ExcelIgnore
    private String idCardPhotoBack;
    /**
     * 籍贯
     */
    //@ExcelAttribute(sort =6)
    @ExcelProperty(index = 7 ,value = "籍贯")
    private String nativePlace;
    /**
     * 民族
     */
    @ExcelIgnore
    private String nation;
    /**
     * 英文名
     */
    @ExcelIgnore
    private String englishName;
    /**
     * 婚姻状况
     */
    @ExcelIgnore
    private String maritalStatus;
    /**
     * 员工照片
     */
    @ExcelIgnore
    private String staffPhoto;
    /**
     * 生日
     */
    //@ExcelAttribute(sort =7)
    @ExcelProperty(index = 3 ,value = "出生日期")
    private String birthday;
    /**
     * 属相
     */
    //@ExcelAttribute(sort =8)
    @ExcelProperty(index = 8 ,value = "属相")
    private String zodiac;
    /**
     * 年龄
     */
    @ExcelIgnore
    private String age;
    /**
     * 星座
     */
    @ExcelIgnore
    private String constellation;
    /**
     * 血型
     */
    @ExcelIgnore
    private String bloodType;
    /**
     * 户籍所在地
     */
    @ExcelIgnore
    private String domicile;
    /**
     * 政治面貌
     */
    @ExcelIgnore
    private String politicalOutlook;
    /**
     * 入党时间
     */
    @ExcelIgnore
    private String timeToJoinTheParty;
    /**
     * 存档机构
     */
    @ExcelIgnore
    private String archivingOrganization;
    /**
     * 子女状态
     */
    @ExcelIgnore
    private String stateOfChildren;
    /**
     * 子女有无商业保险
     */
    @ExcelIgnore
    private String doChildrenHaveCommercialInsurance;
    /**
     * 有无违法违纪行为
     */
    @ExcelIgnore
    private String isThereAnyViolationOfLawOrDiscipline;
    /**
     * 有无重大病史
     */
    @ExcelIgnore
    private String areThereAnyMajorMedicalHistories;
    /**
     * QQ
     */
    @ExcelIgnore
    private String qq;
    /**
     * 微信
     */
    @ExcelIgnore
    private String wechat;
    /**
     * 居住证城市
     */
    @ExcelIgnore
    private String residenceCardCity;
    /**
     * 居住证办理日期
     */
    @ExcelIgnore
    private String dateOfResidencePermit;
    /**
     * 居住证截止日期
     */
    @ExcelIgnore
    private String residencePermitDeadline;
    /**
     * 现居住地
     */
    @ExcelIgnore
    private String placeOfResidence;
    /**
     * 通讯地址
     */
    @ExcelIgnore
    private String postalAddress;
    /**
     * 联系手机
     */
    @ExcelIgnore
    private String contactTheMobilePhone;
    /**
     * 个人邮箱
     */
    @ExcelIgnore
    private String personalMailbox;
    /**
     * 紧急联系人
     */
    @ExcelIgnore
    private String emergencyContact;
    /**
     * 紧急联系电话
     */
    @ExcelIgnore
    private String emergencyContactNumber;
    /**
     * 社保电脑号
     */
    @ExcelIgnore
    private String socialSecurityComputerNumber;
    /**
     * 公积金账号
     */
    @ExcelIgnore
    private String providentFundAccount;
    /**
     * 银行卡号
     */
    @ExcelIgnore
    private String bankCardNumber;
    /**
     * 开户行
     */
    @ExcelIgnore
    private String openingBank;
    /**
     * 学历类型
     */
    @ExcelIgnore
    private String educationalType;
    /**
     * 毕业学校
     */
    @ExcelIgnore
    private String graduateSchool;
    /**
     * 入学时间
     */
    @ExcelIgnore
    private String enrolmentTime;
    /**
     * 毕业时间
     */
    @ExcelIgnore
    private String graduationTime;
    /**
     * 专业
     */
    @ExcelIgnore
    private String major;
    /**
     * 毕业证书
     */
    @ExcelIgnore
    private String graduationCertificate;
    /**
     * 学位证书
     */
    @ExcelIgnore
    private String certificateOfAcademicDegree;
    /**
     * 上家公司
     */
    @ExcelIgnore
    private String homeCompany;
    /**
     * 职称
     */
    @ExcelIgnore
    private String title;
    /**
     * 简历
     */
    @ExcelIgnore
    private String resume;
    /**
     * 有无竞业限制
     */
    @ExcelIgnore
    private String isThereAnyCompetitionRestriction;



    /**
     * 前公司离职证明
     */
    @ExcelIgnore
    private String proofOfDepartureOfFormerCompany;
    /**
     * 备注
     */
    @ExcelIgnore
    private String remarks;

    /**
     * 离职时间
     */
    //@ExcelAttribute(sort =12)
    @ExcelProperty(index = 9 ,value = "离职时间")
    private String resignationTime;
    /**
     * 离职类型
     */
    //@ExcelAttribute(sort =10)
    @ExcelProperty(index = 10 ,value = "离职类型")
    private String typeOfTurnover;
    /**
     * 申请离职原因
     */
    //@ExcelAttribute(sort =11)
    @ExcelProperty(index = 11 ,value = "申请离职原因")
    private String reasonsForLeaving;

}
