package com.lansea.wms.service;

import com.lansea.wms.mapper.ApproveLogMapper;
import com.lansea.wms.model.ApproveLog;
import com.lansea.wms.model.Move;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveLogService extends BaseService {

    @Autowired
    ApproveLogMapper approveLogMapper;

    /**
     * 添加出入库审批日志
     *
     * @param stockEntry 出入库
     * @return
     */
    public ApproveLog addStockEntryLog(StockEntry stockEntry) {
        ApproveLog approveLog = new ApproveLog();
        approveLog.setType(1);
        String remark = stockEntry.getRemark();
        if (stockEntry.getStatus() == 4) {
            remark = stockEntry.getRejectRemark();
        }
        approveLogMapper.insert(setLogInfo(approveLog, stockEntry.getStatus(), stockEntry.getId(), remark));
        return approveLog;
    }

    /**
     * 添加以为日志
     * @param move
     * @return
     */
    public ApproveLog addMoveLog(Move move) {
        ApproveLog approveLog = new ApproveLog();
        approveLog.setType(2);
        String remark = move.getRemark();
        if (move.getStatus() == 4) {
            remark = move.getRejectRemark();
        }
        approveLogMapper.insert(setLogInfo(approveLog, move.getStatus(), move.getId(), remark));
        return approveLog;
    }

    private ApproveLog setLogInfo(ApproveLog approveLog, Integer status, Integer pid, String remark) {
        approveLog.setPid(pid);
        approveLog.setStatus(status);
        approveLog.setCreateUidToLoginUser(userService);
        approveLog.setRemark(remark);
        return approveLog;
    }
}
