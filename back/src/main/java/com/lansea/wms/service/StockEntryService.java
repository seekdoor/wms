package com.lansea.wms.service;

import com.lansea.wms.form.StockEntryDeliveryFinishForm;
import com.lansea.wms.mapper.DeliveryMapper;
import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.Delivery;
import com.lansea.wms.model.Move;
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


    @Transactional(rollbackFor = Exception.class)
    public Integer insert(StockEntry stockEntry) {
        stockEntry.setStatus(1);
        Integer num = stockEntryMapper.insert(stockEntry);
        approveLogService.addStockEntryLog(stockEntry);
        return num;
    }

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
            move.setDeliveryId(stockEntry.getDeliveryId());
            moveService.finish(move);
        }
        return updateStatus(stockEntry);
    }

    /**
     * 修改审批状态
     *
     * @param stockEntry 订单
     * @return
     */
    public Integer updateStatus(StockEntry stockEntry) {
        approveLogService.addStockEntryLog(stockEntry);
        return stockEntryMapper.updateStatus(stockEntry);
    }

    /**
     * 提交审核
     *
     * @param stockEntry 订单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer submit(StockEntry stockEntry) {
        stockEntry.setStatus(2);
        return updateStatus(stockEntry);
    }

    @Autowired
    DeliveryMapper deliveryMapper;

    /**
     * 完成发货
     *
     * @param form
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer deliveryFinish(StockEntryDeliveryFinishForm form) {
        Delivery delivery = form.getDelivery();
        delivery.setCreateUidToLoginUser(userService);
        deliveryMapper.insert(delivery);
        StockEntry stockEntry = form.getStockEntry();
        stockEntry.setDeliveryId(delivery.getId());
        return finish(stockEntry);
    }

    /**
     * 删除出入库订单
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(String ids) {
        moveMapper.deleteByStockEntryIds(ids);
        return stockEntryMapper.delete(ids);
    }

}
