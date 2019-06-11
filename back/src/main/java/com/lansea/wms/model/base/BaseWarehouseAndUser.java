package com.lansea.wms.model.base;

import javax.validation.constraints.Min;

public class BaseWarehouseAndUser extends BaseUser {

    @Min(value = 1, message = "请选择仓库")
    private Integer warehouseId;

    @Min(value = 1, message = "请选择库位")
    private Integer reservoirId;

    @Min(value = 1, message = "请选择货架")
    private Integer stockId;


    private String warehouseName;
    private String reservoirName;
    private String stockName;


    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(Integer reservoirId) {
        this.reservoirId = reservoirId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
