package com.lansea.wms.model;

import com.lansea.wms.model.base.BaseBetweenTime;
import com.lansea.wms.util.DateUtil;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@ApiModel(value = "LoginRecord", description = "登录日志")
public class LoginRecord extends BaseBetweenTime {

    private Integer id;
    private Integer uid;
    private Integer type;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date createTime;
    private String ip;

    private String userName;
    private String realName;


    public String getTypeText() {
        return type == 1 ? "用户登录" : "退出登录";
    }

    // ---------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
