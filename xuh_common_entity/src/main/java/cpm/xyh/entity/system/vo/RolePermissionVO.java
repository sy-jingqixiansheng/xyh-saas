package cpm.xyh.entity.system.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/13 0013 17:24
 * @Version 1.0
 */
@Data
public class RolePermissionVO implements Serializable {

    private String id;
    private List<String> permIds;
}
