package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.model.base.BaseWareUserTime;
import com.lansea.wms.util.DateUtil;
import com.lansea.wms.validate.CodeValidate;
import com.lansea.wms.validate.RemarkValidate;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import java.util.Date;

@Repository
@ApiModel(value = "StockTrans", description = "移位记录")
public class StockTrans extends BaseWareUserTime {

    @Min(value = 1, message = "id 非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @CodeValidate(message = "单号长度必须为 2-40")
    private String number;
    private Integer status;

    @RemarkValidate
    private String remark;

    private Integer warehouseTid;
    private Integer reservoirTid;

    @Min(value = 1, message = "请选择移动货架")
    private Integer stockTid;

    private String warehouseToName;
    private String reservoirToName;
    private String stockToName;

    private String warehouseToCode;
    private String reservoirToCode;

    @Min(value = 1, message = "请选择目标货架")
    private String stockToCode;

    private Integer moveCount;

    private Integer approveUid;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date approveTime;

    private String rejectRemark;

    private String approveUserName;


    /**
     * 是否允许提交
     * @return
     */
    public boolean canSubmit() {
        if (status == null) return false;
        return status == 1 || status == 4;
    }

    /**
     * 是否允许审批
     *
     * @return
     */
    public boolean canApprove() {
        if (status == null) return false;
        return status == 2;
    }

    /**
     * 是否允许完成
     *
     * @return
     */
    public boolean canFinish() {
        if (status == null) return false;
        return status == 3;
    }



    // ################################################################################

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

    public Integer getApproveUid() {
        return approveUid;
    }

    public void setApproveUid(Integer approveUid) {
        this.approveUid = approveUid;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName;
    }

    public String getRejectRemark() {
        return rejectRemark;
    }

    public void setRejectRemark(String rejectRemark) {
        this.rejectRemark = rejectRemark;
    }
}
