package com.lansea.wms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.DateUtil;
import com.lansea.wms.validate.CodeValidate;
import com.lansea.wms.validate.RemarkValidate;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import java.util.Date;

@Repository
@ApiModel(value = "Delivery", description = "发货物流")
public class Delivery extends BaseUser {

    @Min(value = 1, message = "id 非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @Min(value = 1, message = "请选择出库单")
    private Integer stockEntryId;

    @Min(value = 1, message = "请选择承运商")
    private Integer companyId;

    @CodeValidate(message = "快递单号长度为 2-40")
    private String number;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    @JsonFormat(pattern = DateUtil.DATE_FORMAT)
    private Date happenTime;

    @RemarkValidate
    private String remark;

    private String stockEntryNumber;
    private String companyCode;
    private String companyName;
    private String companyAddress;
    private String companyUserName;
    private String companyPhone;
    private String companyMobile;
    private String companyEmail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockEntryId() {
        return stockEntryId;
    }

    public void setStockEntryId(Integer stockEntryId) {
        this.stockEntryId = stockEntryId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStockEntryNumber() {
        return stockEntryNumber;
    }

    public void setStockEntryNumber(String stockEntryNumber) {
        this.stockEntryNumber = stockEntryNumber;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
