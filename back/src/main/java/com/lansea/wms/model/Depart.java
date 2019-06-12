package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.util.DateUtil;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import java.util.Date;

@Repository
@ApiModel(value = "Depart", description = "公司部门")
public class Depart {

    @Min(value = 1, message = "id非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @Length(min = 2, max = 40, message = "编码长度必须为 2-40")
    private String code;

    @Length(min = 2, max = 40, message = "部门名称长度必须为 2-40")
    private String name;
    private Integer createUid;
    private Integer updateUid;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date createTime;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date updateTime;

    private String createUserName;
    private String updateUserName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
