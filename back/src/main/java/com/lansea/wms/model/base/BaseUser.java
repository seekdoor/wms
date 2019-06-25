package com.lansea.wms.model.base;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lansea.wms.entity.SerializeBase;
import com.lansea.wms.service.UserService;
import com.lansea.wms.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseUser extends SerializeBase {

    private Integer createUid;
    private Integer updateUid;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date createTime;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date updateTime;

    private String createUserName;
    private String updateUserName;


    public void setCreateUidToLoginUser(UserService userService) {
        setCreateUid(userService.getLoginUser().getId());
    }

    public void setUpdateUidToLoginUser(UserService userService) {
        setUpdateUid(userService.getLoginUser().getId());
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
