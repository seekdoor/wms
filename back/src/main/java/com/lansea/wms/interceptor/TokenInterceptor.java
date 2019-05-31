package com.lansea.wms.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.Token;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String auth = request.getHeader("Authorization");
            if (auth == null) {
                throw new Exception("Token 为空");
            }
            User user = Token.createToken(auth).findUser();
            if (user == null) {
                throw new Exception("Token 用户不存在");
            }
            request.setAttribute("loginUser", user);
        } catch (Exception e) {
            Result result = Result.error(e.getMessage(), 110);
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
