package com.lansea.wms.service;

import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.Move;
import com.lansea.wms.model.Stock;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockEntryService extends BaseService {

    @Autowired
    StockEntryMapper stockEntryMapper;

    @Autowired
    MoveMapper moveMapper;

    @Autowired
    MoveService moveService;

    /**
     * 出入库单 审批
     *
     * @param stockEntry 出入库单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer approve(StockEntry stockEntry) {
        stockEntry.setStatus(stockEntry.getStatus());
        stockEntry.setApproveUid(userService.getLoginUser().getId());
        moveMapper.updateStatusByStockEntry(stockEntry);
        return updateStatus(stockEntry);
    }

    /**
     * 完成订单
     *
     * @param stockEntry 出入库单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer finish(StockEntry stockEntry) {
        stockEntry.setStatus(5);
        List<Move> moves = moveMapper.selectByStockEntryId(stockEntry.getId());
        for (Move move : moves) {
            moveService.finish(move);
        }
        return updateStatus(stockEntry);
    }

    /**
     * 修改状态
     *
     * @param stockEntry 订单
     * @return
     */
    public Integer updateStatus(StockEntry stockEntry) {
        return stockEntryMapper.updateStatus(stockEntry);
    }

    /**
     * 提交审核
     *
     * @param stockEntry
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer submit(StockEntry stockEntry) {
        stockEntry.setStatus(2);
        approve(stockEntry);
        return stockEntryMapper.updateStatus(stockEntry);

    }

}
