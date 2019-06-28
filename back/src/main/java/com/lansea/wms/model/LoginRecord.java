package com.lansea.wms.model;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@ApiModel(value = "LoginRecord", description = "登录日志")
public class LoginRecord {

    private Integer id;
    private Integer uid;
    private Integer type;
    private Date createTime;


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

}
