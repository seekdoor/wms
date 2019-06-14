package com.lansea.wms.model;


import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Move extends BaseUser {

    private Integer id;
    private Integer type;
    private Integer inventoryId;
    private Integer inventoryTid;
    private Integer companyId;
    private Integer stockEntryId;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date happenDate;

    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getInventoryTid() {
        return inventoryTid;
    }

    public void setInventoryTid(Integer inventoryTid) {
        this.inventoryTid = inventoryTid;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStockEntryId() {
        return stockEntryId;
    }

    public void setStockEntryId(Integer stockEntryId) {
        this.stockEntryId = stockEntryId;
    }
}
