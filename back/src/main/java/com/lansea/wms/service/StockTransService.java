package com.lansea.wms.service;


import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.mapper.StockTransMapper;
import com.lansea.wms.model.Move;
import com.lansea.wms.model.StockTrans;
import com.lansea.wms.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockTransService extends BaseService {

    @Autowired
    StockTransMapper stockTransMapper;

    @Autowired
    MoveService moveService;

    @Autowired
    MoveMapper moveMapper;

    @Autowired
    NumberCreateService numberCreateService;

    /**
     * 新增移库单
     *
     * @param form 移库表单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(StockTrans form) {
        form.setNumber(numberCreateService.create("stock_trans"));
        Integer num = stockTransMapper.insert(form);
        approveLogService.addStockTransLog(form);
        return num;
    }

    /**
     * 修改移位信息
     *
     * @param form 移库表单
     * @return
     */
    public Integer update(StockTrans form) {
        form.setMoveCount(form.getMoveCount());
        return stockTransMapper.update(form);
    }

    /**
     * 删除移位信息
     *
     * @param form ids表单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(DeleteIdsForm form) {
        moveMapper.deleteByStockTransIds(form.getIds());
        return stockTransMapper.delete(form.getIds());
    }

    /**
     * 提交审核
     *
     * @param stockTrans
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer submit(StockTrans stockTrans) {
        stockTrans.setStatus(2);
        moveMapper.updateStatusByStockTrans(stockTrans);
        return updateStatus(stockTrans);
    }

    /**
     * 审核移位
     *
     * @param stockTrans
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer approve(StockTrans stockTrans) {
        moveMapper.updateStatusByStockTrans(stockTrans);
        return updateStatus(stockTrans);
    }

    /**
     * 完成移库操作
     *
     * @param stockTrans
     * @return
     */
    public Integer finish(StockTrans stockTrans) {
        stockTrans.setStatus(5);
        List<Move> moves = moveMapper.selectByStockTransId(stockTrans.getId());
        for (Move move : moves) {
            move.setStatus(stockTrans.getStatus());
            moveService.finish(move);
        }
        return updateStatus(stockTrans);
    }

    /**
     * 修改审批状态
     *
     * @param stockTrans
     * @return
     */
    private Integer updateStatus(StockTrans stockTrans) {
        approveLogService.addStockTransLog(stockTrans);
        return stockTransMapper.updateStatus(stockTrans);
    }


}
