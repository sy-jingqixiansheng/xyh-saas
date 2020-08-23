package com.xyh.common.controller;

import com.xyh.common.entity.resqonse.ProfileResultDTO;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author SQ  公共controller  获取request，response
 * 获取企业id，获取企业名称
 * @Date 2020/8/11 0011 10:10
 * @Version 1.0
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Claims claims;
    protected String companyId;
    protected String companyName;

                //使用Shiro方式获取
    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        //Shiro方式从session中获取安全数据
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        if(principals!=null && !principals.isEmpty()){
            ProfileResultDTO resultDTO = (ProfileResultDTO) principals.getPrimaryPrincipal();
            this.companyId = resultDTO.getCompanyId();
            this.companyName = resultDTO.getCompany();
        }
        }
    }

                //JWT获取方式
//    @ModelAttribute
//    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response)
//    {
//        this.request = request;
//        this.response = response;
//
//        Object obj = request.getAttribute("user_claims");
//        if(obj != null){
//            this.claims = (Claims) obj;
//            this.companyId = (String)claims.get("companyId");
//            this.companyName = (String)claims.get("companyName");
//        }
//    }

