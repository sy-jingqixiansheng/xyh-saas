package cpm.xyh.entity.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author SQ  接收分页数据条件类
 * @Date 2020/8/12 0012 11:40
 * @Version 1.0
 */
@Data
public class UserPageReqVO {
    @ApiModelProperty(value = "当前第几页")
    private Integer page;

    @ApiModelProperty(value = "当前页数量")
    private Integer size;

    @ApiModelProperty(value = "部门id")
    private String departmentId;

    @ApiModelProperty(value = "企业id")
    private String companyId;

    @ApiModelProperty(value = "用户名字")
    private String username;

    @ApiModelProperty(value = "用户名字")
    private String userId;
}