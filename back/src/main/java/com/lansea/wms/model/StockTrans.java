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

    @CodeValidate(message = "单号长度必须为 2-40", groups = {ValidClass.EditForm.class})
    private String number;
    private Integer status;

    @RemarkValidate
    private String remark;

    private Integer warehouseTid;
    private Integer reservoirTid;

    @Min(value = 1, message = "请选择源货架")
    private Integer stockId;

    @Min(value = 1, message = "请选择目标货架")
    private Integer stockTid;

    private String warehouseNameTo;
    private String reservoirNameTo;
    private String stockNameTo;

    private String warehouseCodeTo;
    private String reservoirCodeTo;
    private String stockCodeTo;

    private Integer moveCount;

    private Integer approveUid;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date approveTime;

    private String rejectRemark;

    private String approveUserName;


    /**
     * 是否允许提交
     *
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

    @Override
    public Integer getStockId() {
        return stockId;
    }

    @Override
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getWarehouseNameTo() {
        return warehouseNameTo;
    }

    public void setWarehouseNameTo(String warehouseNameTo) {
        this.warehouseNameTo = warehouseNameTo;
    }

    public String getReservoirNameTo() {
        return reservoirNameTo;
    }

    public void setReservoirNameTo(String reservoirNameTo) {
        this.reservoirNameTo = reservoirNameTo;
    }

    public String getStockNameTo() {
        return stockNameTo;
    }

    public void setStockNameTo(String stockNameTo) {
        this.stockNameTo = stockNameTo;
    }

    public String getWarehouseCodeTo() {
        return warehouseCodeTo;
    }

    public void setWarehouseCodeTo(String warehouseCodeTo) {
        this.warehouseCodeTo = warehouseCodeTo;
    }

    public String getReservoirCodeTo() {
        return reservoirCodeTo;
    }

    public void setReservoirCodeTo(String reservoirCodeTo) {
        this.reservoirCodeTo = reservoirCodeTo;
    }

    public String getStockCodeTo() {
        return stockCodeTo;
    }

    public void setStockCodeTo(String stockCodeTo) {
        this.stockCodeTo = stockCodeTo;
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

    public String getRejectRemark() {
        return rejectRemark;
    }

    public void setRejectRemark(String rejectRemark) {
        this.rejectRemark = rejectRemark;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName;
    }
}
