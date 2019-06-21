package com.lansea.wms.service;

import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockEntryService extends BaseService {

    @Autowired
    StockEntryMapper stockEntryMapper;

    @Autowired
    MoveMapper moveMapper;

    /**
     * 出入库单 审批
     *
     * @param stockEntry 出入库单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer approve(StockEntry stockEntry) {
        moveMapper.updateStatusByStockEntry(stockEntry);
        return stockEntryMapper.updateStatus(stockEntry);
    }

}
