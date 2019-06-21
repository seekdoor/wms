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
        approveLog.setPid(stockEntry.getId());
        approveLog.setStatus(stockEntry.getStatus());
        approveLog.setCreateUidToLoginUser(userService);
        approveLogMapper.insert(approveLog);
        return approveLog;
    }

    public ApproveLog addMoveLog(Move move){
        ApproveLog approveLog = new ApproveLog();
        approveLog.setType(1);
        approveLog.setPid(move.getId());
        approveLog.setStatus(move.getStatus());
        approveLog.setCreateUidToLoginUser(userService);
        approveLogMapper.insert(approveLog);
        return approveLog;
    }

}
