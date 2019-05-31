package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import java.util.Date;

@Repository
@ApiModel(value = "Role", description = "角色")
public class Role {

    @Min(value = 1, message = "id非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @Length(min = 2, max = 40, message = "名称长度为 2-40")
    private String name;

    @Length(max = 400, message = "备注长度为 2-400")
    private String remark;
    private Integer createUid;
    private Integer updateUid;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date createTime;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date updateTime;

    private String createUserName;
    private String updateUserName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
