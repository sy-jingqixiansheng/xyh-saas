package com.xyh.common.entity.resqonse;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author SQ 返回前端 用户个人信息  权限信息
 * @Date 2020/8/14 0014 13:56
 * @Version 1.0
 */
@Getter
@Setter
public class ProfileResultDTO implements Serializable, AuthCachePrincipal {
    private  String userId;
    private  String mobile;
    private  String username;
    private  String company;
    private String companyId;
    private  Map<String,Object> roles = new HashMap<>();


    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
