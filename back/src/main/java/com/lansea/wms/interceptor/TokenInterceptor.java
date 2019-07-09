package com.lansea.wms.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.Token;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import com.lansea.wms.service.AuthValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthValidateService authValidateService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int code = 110;
        try {
            String auth = request.getHeader("Authorization");
            if (auth == null) {
                throw new Exception("Token 为空");
            }
            User user = Token.createToken(auth).findUser();
            if (user == null) {
                throw new Exception("Token 用户不存在");
            }
            if (user.getActivated() == 1) {
                throw new Exception("您的账户尚未激活，请联系管理员激活!");
            }
            request.setAttribute("loginUser", user);
            if (!authValidateService.validate()) {
                code = 120;
                throw new Exception("您的账户权限不足，请联系管理员开通！");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Result result = Result.error(e.getMessage(), code);
            response.getWriter().print(JSONObject.toJSONString(result));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
