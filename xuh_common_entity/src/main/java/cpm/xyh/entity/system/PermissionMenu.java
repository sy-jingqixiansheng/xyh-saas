package cpm.xyh.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单的权限
 */
@Data
public class PermissionMenu implements Serializable {

    /**
     * 主键
     */

    private String id;

    //展示图标
    private String menuIcon;

    //排序号
    private String menuOrder;
}