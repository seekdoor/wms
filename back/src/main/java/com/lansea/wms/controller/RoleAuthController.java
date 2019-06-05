package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.AuthMapper;
import com.lansea.wms.mapper.RoleAuthMapper;
import com.lansea.wms.mapper.RoleMapper;
import com.lansea.wms.model.Auth;
import com.lansea.wms.model.Role;
import com.lansea.wms.service.PageService;
import com.lansea.wms.service.RoleAuthService;
import com.lansea.wms.service.RoleService;
import com.lansea.wms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/role_auth")
@Api(description = "角色权限管理")
public class RoleAuthController {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthMapper authMapper;

    @Autowired
    PageService pageService;

    @Autowired
    UserService userService;

    @Autowired
    RoleAuthMapper roleAuthMapper;

    @Autowired
    RoleAuthService roleAuthService;

    @GetMapping("/role_list")
    @ApiOperation(value = "角色列表", notes = "分页")
    Result roleList(Role role) {
        pageService.setPaginate();
        List<Role> list = roleMapper.selectWhere(role, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping("/role_all")
    @ApiOperation(value = "所有角色")
    Result roleAll() {
        return Result.success(roleMapper.selectAll());
    }

    @GetMapping("/get_role_by_id")
    @ApiOperation(value = "根据id获取角色")
    Result getRoleById(Integer id) {
        Role role = roleMapper.findById(id);
        role.setAuths(authMapper.selectAllByRoleId(role.getId()));
        return Result.success(role);
    }

    @PostMapping("/role_insert")
    @ApiOperation(value = "添加角色")
    Result roleInsert(@Validated @RequestBody Role role, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        role.setCreateUid(userService.getLoginUser().getId());
        roleMapper.insert(role);
        roleAuthMapper.insertAll(roleAuthService.createList(role));
        return Result.success("添加角色成功");
    }

    @PostMapping("/role_update")
    @ApiOperation(value = "更新角色")
    Result roleUpdate(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Role role, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        role.setUpdateUid(userService.getLoginUser().getId());
        roleMapper.update(role);
        roleService.resetRoleAuth(role);
        return Result.success("更新角色成功");
    }

    @PostMapping("/role_delete")
    @ApiOperation(value = "删除角色")
    Result roleDelete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = roleMapper.deleteByIds(form.getIds());
        return Result.success("共删除 " + num + " 条角色信息");
    }

    // 一下为权限接口


    @GetMapping("/auth_all")
    @ApiOperation(value = "所有权限列表")
    Result authAll(Integer pid) {
        return Result.success(authMapper.selectAll(pid));
    }

    @GetMapping("/get_auth_by_id")
    @ApiOperation(value = "根据id获取权限")
    Result getAuthById(Integer id) {
        return Result.success(authMapper.findById(id));
    }

    @PostMapping("/auth_insert")
    @ApiOperation(value = "新增权限")
    Result authInsert(@Validated @RequestBody Auth auth, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        authMapper.insert(auth);
        return Result.success("新增权限成功");
    }

    @PostMapping("/auth_update")
    @ApiOperation(value = "更新权限")
    Result authUpdate(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Auth auth, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        authMapper.update(auth);
        return Result.success("权限更新成功");
    }

    @PostMapping("/auth_delete")
    @ApiOperation(value = "删除权限")
    Result authDelete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = authMapper.deleteByIds(form.getIds());
        roleAuthMapper.deleteByAuthIds(String.valueOf(form.getIds()));
        return Result.success("共删除 " + num + " 条权限");
    }
}
