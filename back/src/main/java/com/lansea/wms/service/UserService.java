package com.lansea.wms.service;

import com.lansea.wms.entity.Token;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    HttpServletRequest request;

    /**
     * 刷新用户在数据库中的  token
     * @param user  用户
     */
    public void refreshUserToken(User user) {
        Token token = new Token(user);
        user.setToken(token.createTokenEnString());
        userMapper.refreshToken(user);
    }

    /**
     * 获取登录用户
     * @return
     */
    public User getLoginUser() {
        return (User) request.getAttribute("loginUser");
    }
}
