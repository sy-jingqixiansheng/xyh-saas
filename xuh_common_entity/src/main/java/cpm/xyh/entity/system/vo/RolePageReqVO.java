package cpm.xyh.entity.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: RolePageReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class RolePageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum ;
    @ApiModelProperty(value = "当前页的数量")
    private int pageSize;


    @ApiModelProperty(value = "角色名称")
    private String name;
    @ApiModelProperty(value = "角色状态")
    private String companyId;
}
