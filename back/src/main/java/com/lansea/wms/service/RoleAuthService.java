package com.lansea.wms.service;

import com.lansea.wms.model.Auth;
import com.lansea.wms.model.Role;
import com.lansea.wms.model.RoleAuth;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleAuthService {

    /**
     * 将提交上来的带有 权限列表的 角色对象
     * 转换成 RoleAuth 中间表列表
     *
     * @param role
     * @return
     */
    public List<RoleAuth> createList(Role role) {
        List<RoleAuth> roleAuths = new ArrayList<>();
        if (role == null) {
            return roleAuths;
        }
        for (Auth auth : role.getAuths()) {
            roleAuths.add(new RoleAuth(role.getId(), auth.getId()));
        }
        return roleAuths;
    }

}
