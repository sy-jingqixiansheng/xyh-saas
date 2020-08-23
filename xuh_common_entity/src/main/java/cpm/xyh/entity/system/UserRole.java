package cpm.xyh.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author SQ
 * @Date 2020/8/12 0012 8:11
 * @Version 1.0
 */

@Data
public class UserRole implements Serializable {

    private String userId;

    private String roleId;
}
