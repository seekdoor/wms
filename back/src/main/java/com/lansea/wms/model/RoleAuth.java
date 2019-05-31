package com.lansea.wms.model;

import org.springframework.stereotype.Repository;

@Repository
public class RoleAuth {

    private Integer roleId;
    private Integer authId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}
