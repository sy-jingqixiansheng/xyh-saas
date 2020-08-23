package cpm.xyh.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * Api权限的权限
 */
@Data
public class PermissionApi implements Serializable {
    /**
     * 主键
     */

    private String id;
    /**
     * 链接
     */
    private String apiUrl;
    /**
     * 请求类型
     */
    private String apiMethod;
    /**
     * 权限等级，1为通用接口权限，2为需校验接口权限
     */
    private String apiLevel;
}