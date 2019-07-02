package com.lansea.wms.model;

import com.lansea.wms.entity.SerializeBase;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.enums.CompanyCategoryEnum;
import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.DateUtil;
import com.lansea.wms.util.EnumUtil;
import com.lansea.wms.validate.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Repository
@ApiModel(value = "Company", description = "第三方公司（客户，供应商）模型")
public class Company extends BaseUser {

    @ApiModelProperty(value = "ID")
    @Min(value = 1, message = "ID 非法", groups = {ValidClass.EditForm.class, ValidClass.FindIdForm.class})
    private Integer id;

    @ApiModelProperty(value = "编码")
    @Length(min = 2, max = 40, message = "编码长度必须为 2 - 40")
    private String code;

    @ApiModelProperty(value = "分类 1:供应商，2：客户，3：承运商")
    @Min(value = 1, message = "分类非法")
    private Integer category;

    @ApiModelProperty(value = "公司全名")
    @Length(min = 2, max = 200, message = "公司全称必须为 2 - 200")
    private String name;

    @Length(min = 2, max = 200, message = "地址必须为 2 - 200")
    private String address;

    @ApiModelProperty(value = "联系人名称")
    @Length(min = 2, max = 40, message = "联系人长度为 2 - 40")
    private String contactName;

    @ApiModelProperty(value = "座机")
    private String phone;

    @NotBlank(message = "手机号不能为空")
    @Mobile
    private String mobile;
    @Email(message = "email格式错误")
    private String email;

    private String remark;

    // 扩展字段

    @ApiModelProperty(value = "创建人")
    private String createUserName;
    @ApiModelProperty(value = "审批人")
    private String updateUserName;
    @ApiModelProperty(value = "分类名称")
    private String categoryName;



    public String getCategoryName() {
        return EnumUtil.valueOf(CompanyCategoryEnum.class, category).getValue();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
