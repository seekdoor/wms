package com.lansea.wms.model;

import com.lansea.wms.model.base.BaseWareUserTime;

public class StockTrans extends BaseWareUserTime {

    private Integer id;
    private String number;
    private Integer status;
    private String remark;
    private Integer warehouseTid;
    private Integer reservoirTid;
    private Integer stockTid;

    private String warehouseToName;
    private String reservoirToName;
    private String stockToName;

    private String warehouseToCode;
    private String reservoirToCode;
    private String stockToCode;

    private Integer moveCount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getWarehouseTid() {
        return warehouseTid;
    }

    public void setWarehouseTid(Integer warehouseTid) {
        this.warehouseTid = warehouseTid;
    }

    public Integer getReservoirTid() {
        return reservoirTid;
    }

    public void setReservoirTid(Integer reservoirTid) {
        this.reservoirTid = reservoirTid;
    }

    public Integer getStockTid() {
        return stockTid;
    }

    public void setStockTid(Integer stockTid) {
        this.stockTid = stockTid;
    }

    public String getWarehouseToName() {
        return warehouseToName;
    }

    public void setWarehouseToName(String warehouseToName) {
        this.warehouseToName = warehouseToName;
    }

    public String getReservoirToName() {
        return reservoirToName;
    }

    public void setReservoirToName(String reservoirToName) {
        this.reservoirToName = reservoirToName;
    }

    public String getStockToName() {
        return stockToName;
    }

    public void setStockToName(String stockToName) {
        this.stockToName = stockToName;
    }

    public String getWarehouseToCode() {
        return warehouseToCode;
    }

    public void setWarehouseToCode(String warehouseToCode) {
        this.warehouseToCode = warehouseToCode;
    }

    public String getReservoirToCode() {
        return reservoirToCode;
    }

    public void setReservoirToCode(String reservoirToCode) {
        this.reservoirToCode = reservoirToCode;
    }

    public String getStockToCode() {
        return stockToCode;
    }

    public void setStockToCode(String stockToCode) {
        this.stockToCode = stockToCode;
    }

    public Integer getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(Integer moveCount) {
        this.moveCount = moveCount;
    }
}
