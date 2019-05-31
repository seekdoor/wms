package com.lansea.wms.form;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.model.User;
import com.lansea.wms.validate.Password;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "UserAddForm", description = "添加用户表单")
public class UserAddForm extends User {

    @ApiModelProperty(value = "重复密码")
    @NotBlank(message = "重复密码不能为空", groups = {ValidClass.AddForm.class})
    @Password(message = "重复密码格式错误")
    private String rePassword;

    public boolean verifyRePassword() {
        return getPassword().equals(rePassword);
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
