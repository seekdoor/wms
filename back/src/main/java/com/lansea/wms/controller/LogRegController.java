package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import com.lansea.wms.service.LoginRecordService;
import com.lansea.wms.service.UserService;
import com.lansea.wms.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log_reg")
@Api(description = "登录注册")
public class LogRegController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    LoginRecordService loginRecordService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    Result login(@Validated({User.Login.class}) @RequestBody User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.errorByBindingResult(bindingResult);
        }
        User user = userMapper.findByUserName(userForm.getUserName());
        if (user == null ) {
            return Result.error("用户不存在");
        }
        if( user.getActivated() == 1 ){
            return Result.error("您的账户尚未激活，请联系管理员激活!");
        }
        if (!MD5Util.verify(userForm.getPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }
        loginRecordService.addLogin(user);
        userService.refreshUserToken(user);
        System.out.println(user);
        return Result.success(user);
    }


}
