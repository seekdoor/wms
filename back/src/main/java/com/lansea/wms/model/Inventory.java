package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.model.base.BaseWarehouseAndUser;
import com.lansea.wms.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Repository
public class Inventory extends BaseWarehouseAndUser {

    @Min(value = 1, message = "id 非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @Min(value = 1, message = "请选择物料")
    private Integer materialId;

    @DecimalMin(value = "0.01", message = "数量必须大于 0.01")
    @DecimalMax(value = "1000000.00", message = "数量必须小于 1000000")
    private BigDecimal quantity;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date lastInTime;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date lastOutTime;

    private BigDecimal lastInQuantity;
    private BigDecimal lastOutQuantity;


    private String materialCode;
    private String materialName;

    private String mtlUnitName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getLastInTime() {
        return lastInTime;
    }

    public void setLastInTime(Date lastInTime) {
        this.lastInTime = lastInTime;
    }

    public Date getLastOutTime() {
        return lastOutTime;
    }

    public void setLastOutTime(Date lastOutTime) {
        this.lastOutTime = lastOutTime;
    }

    public BigDecimal getLastInQuantity() {
        return lastInQuantity;
    }

    public void setLastInQuantity(BigDecimal lastInQuantity) {
        this.lastInQuantity = lastInQuantity;
    }

    public BigDecimal getLastOutQuantity() {
        return lastOutQuantity;
    }

    public void setLastOutQuantity(BigDecimal lastOutQuantity) {
        this.lastOutQuantity = lastOutQuantity;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMtlUnitName() {
        return mtlUnitName;
    }

    public void setMtlUnitName(String mtlUnitName) {
        this.mtlUnitName = mtlUnitName;
    }
}
