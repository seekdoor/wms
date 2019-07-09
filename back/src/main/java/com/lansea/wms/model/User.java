package com.lansea.wms.model;

import com.lansea.wms.entity.SerializeBase;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.util.DateUtil;
import com.lansea.wms.validate.Mobile;
import com.lansea.wms.validate.Password;
import com.lansea.wms.validate.RealName;
import com.lansea.wms.validate.UserName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

@Repository
@ApiModel(value = "User", description = "用户模型")
public class User extends SerializeBase {


    @ApiModelProperty(value = "ID", example = "1")
    @Min(value = 1, message = "id非法", groups = {ValidClass.EditForm.class, ChangeMyInfo.class})
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    @UserName(groups = {Login.class, Default.class})
    private String userName;

    @ApiModelProperty(value = "真实姓名")
    @NotNull(message = "用户名不能为空")
    @RealName
    private String realName;

    @ApiModelProperty(value = "电子邮箱")
    @Email(message = "email格式错误")
    private String email;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空", groups = {Login.class, ValidClass.AddForm.class, ChangeMyInfo.class})
    @Password
    private String password;

    @ApiModelProperty(value = "是否激活")
    private Integer activated;

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    @Mobile
    private String mobile;

    @ApiModelProperty(value = "座机号")
    private String phone;

    @ApiModelProperty(value = "token登录凭证")
    private String token;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date createTime;


    private Integer gender;

    private String avatar;

    @Min(value = 1, message = "请选择角色")
    private Integer roleId;

    @Min(value = 1, message = "请选择部门")
    private Integer departId;

    private String departName;

    private String roleName;

    private Integer isRoot;

    /**
     * 隐藏安全字段
     *
     * @return
     */
    public User hiddenSecurity() {
        password = null;
        token = null;
        return this;
    }

    // 用户登录分组
    public interface Login {
    }

    // 修改我的信息
    public interface ChangeMyInfo {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Integer isRoot) {
        this.isRoot = isRoot;
    }
}
