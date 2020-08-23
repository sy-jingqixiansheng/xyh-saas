package cpm.xyh.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 按钮的权限
 */
@Data
public class PermissionPoint implements Serializable {
    /**
     * 主键
     */

    private String id;

    /**
     * 权限代码
     */
    private String pointClass;

    private String pointIcon;

    private String pointStatus;

}