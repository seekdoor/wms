package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import java.util.List;

@Repository
@ApiModel(value = "Role", description = "角色")
public class Role extends BaseUser {

    @Min(value = 1, message = "id非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @Length(min = 2, max = 40, message = "名称长度为 2-40")
    private String name;

    @Length(max = 400, message = "备注长度为 2-400")
    private String remark;

    private List<Auth> auths;


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

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }
}
