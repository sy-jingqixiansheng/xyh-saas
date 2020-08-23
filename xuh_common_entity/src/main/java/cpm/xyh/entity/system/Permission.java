package cpm.xyh.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限
 */
@Data
public class Permission implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限描述
     */
    private String description;

    private String pid;

    /**
     * 可见状态
     */
    private Integer enVisible;

}