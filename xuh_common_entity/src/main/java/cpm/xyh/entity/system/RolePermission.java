package cpm.xyh.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 8:13
 * @Version 1.0
 */

@Data
public class RolePermission implements Serializable {

    private String roleId;

    private String permissionId;
}
