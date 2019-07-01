package com.lansea.wms.service;

import com.lansea.wms.entity.Token;
import com.lansea.wms.form.UserAddForm;
import com.lansea.wms.mapper.LoginRecordMapper;
import com.lansea.wms.mapper.RoleMapper;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import com.lansea.wms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    LoginRecordService loginRecordService;

    /**
     * 刷新用户在数据库中的  token
     *
     * @param user 用户
     */
    public void refreshUserToken(User user) {
        Token token = new Token(user);
        user.setToken(token.createTokenEnString());
        userMapper.refreshToken(user);
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    public User getLoginUser() {
        return (User) request.getAttribute("loginUser");
    }

    /**
     * 批量隐藏安全字段
     *
     * @param users
     * @return
     */
    public List<User> hiddenSecurity(List<User> users) {
        for (User user : users) {
            user.hiddenSecurity();
        }
        return users;
    }

    /**
     * 用户退出登录
     *
     * @return
     */
    public Integer logOut() {
        loginRecordService.addLogout();
        return userMapper.logout(getLoginUser());
    }

}
