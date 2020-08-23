package cpm.xyh.entity.system;

/**
 * 角色实体类
 */

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
public class Role implements Serializable {

    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;

    private List<String> permIds;

    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多

}