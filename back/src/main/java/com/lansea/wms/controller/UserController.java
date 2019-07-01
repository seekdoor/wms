package com.lansea.wms.controller;


import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.form.UserAddForm;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import com.lansea.wms.service.PageService;
import com.lansea.wms.service.UserService;
import com.lansea.wms.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Api(description = "用户管理")
public class UserController {

    @Autowired
    PageService pageService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "用户列表", notes = "分页接口")
    Result list(User user) {
        pageService.setPaginate();
        List<User> list = userMapper.selectUserWhere(user, pageService.createSort());
        userService.hiddenSecurity(list);
        return Result.successPage(list);
    }

    @GetMapping(value = "/get_user_by_id")
    @ApiOperation(value = "根据id获取单个用户信息")
    Result getUserById(Integer id) {
        return Result.success(userMapper.findById(id).hiddenSecurity());
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加用户")
    Result add(@RequestBody @Validated({ValidClass.AddForm.class, Default.class}) UserAddForm user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        if (user.getRePassword() != null && !user.getPassword().equals(user.getRePassword())) {
            return Result.error("两次密码输入不一致");
        }
        User existUser = userMapper.findByUserName(user.getUserName());
        if (existUser != null) {
            return Result.error("用户名已存在");
        }
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userMapper.insert(user);
        return Result.success("用户添加成功！");
    }

    @PostMapping(value = "/edit")
    @ApiOperation(value = "修改用户信息")
    Result edit(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody UserAddForm user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        if (user.getId() == null || user.getId() < 1) {
            return Result.error("id 非法");
        }
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            if (!user.verifyRePassword()) {
                return Result.error("两次密码输入不一致");
            }
            user.setPassword(MD5Util.getMD5(user.getPassword()));
        }
        userMapper.update(user);
        return Result.success("修改信息成功");
    }

    @PostMapping(value = "edit_password")
    @ApiOperation(value = "修改密码")
    Result editPassword(@RequestBody @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        userMapper.changePassword(user);
        return Result.success("密码修改成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "批量删除用户")
    Result delete(@RequestBody @Validated DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        System.out.println(form);
        Integer num = userMapper.deleteByIds(form.getIds());
        return Result.success("共删除" + num + "条记录");
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录")
    Result logout() {
        userService.logOut();
        return Result.success("已安全退出");
    }

    @PostMapping(value = "/change_my_info")
    @ApiOperation(value = "修改我的个人信息")
    Result changeMyInfo(@Validated({User.ChangeMyInfo.class}) @RequestBody UserAddForm form, BindingResult result){
        if( result.hasErrors() ){
            return Result.errorByBindingResult(result);
        }
        if(!form.verifyRePassword()){
            return Result.error("两次密码输入不一致");
        }
        User user = userService.getLoginUser();
        if(!user.getId().equals(form.getId())){
            return Result.error("非法请求！");
        }
        if( !MD5Util.verify(form.getOldPassword(), user.getPassword()) ){
            return Result.error("原密码错误");
        }
        user.setPassword( MD5Util.getMD5(form.getPassword()));
        userMapper.updatePassword(user);
        return Result.success("修改个人信息成功");
    }

}
