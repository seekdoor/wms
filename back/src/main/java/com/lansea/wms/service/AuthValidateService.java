package com.lansea.wms.service;

import com.lansea.wms.mapper.AuthMapper;
import com.lansea.wms.mapper.RoleMapper;
import com.lansea.wms.model.Auth;
import com.lansea.wms.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthValidateService extends BaseService {

    @Autowired
    AuthMapper authMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    HttpServletRequest request;

    private String requestAddr;

    /**
     * 验证用户权限是否足够
     * 查询auth 权限是否有定义
     * 没有定义则直接通过验证
     * 验证接口是否在登录用户的auth 列表中
     *
     * @return
     */
    public boolean validate() {
        init();
        Auth a = authMapper.findByName(requestAddr);
        if (a == null) {
            return true;
        }
        Auth myAuth = authMapper.findByUserAndAuthName(userService.getLoginUser(), requestAddr);
        return myAuth != null;
    }

    private void init() {
        requestAddr = request.getRequestURI();
    }


}
