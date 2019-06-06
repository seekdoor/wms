package com.lansea.wms.model;

import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.DateUtil;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@ApiModel(value = "Product", description = "产品")
public class Product extends BaseUser {

    private Integer id;
    private String name;
    private Integer categoryId;
    private Integer warehouseId;
    private String code;
    private String spec;
    private Integer unitId;
    private Integer createUid;
    private Integer updateUid;
    private String remark;
    private Warehouse warehouse;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    @Override
    public Integer getCreateUid() {
        return createUid;
    }

    @Override
    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    @Override
    public Integer getUpdateUid() {
        return updateUid;
    }

    @Override
    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
