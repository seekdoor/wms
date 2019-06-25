package com.lansea.wms.service;

import com.lansea.wms.mapper.InventoryMapper;
import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.model.Inventory;
import com.lansea.wms.model.Move;
import com.lansea.wms.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class InventoryService extends BaseService {

    @Autowired
    InventoryMapper inventoryMapper;

    @Autowired
    MoveMapper moveMapper;


    /**
     * 根据出入库单子更新或新增库存情况
     *
     * @param move 出入库单
     * @return
     */
    public Inventory saveInventoryByMove(Move move) {
        Inventory inventory;
        if (move.getInventoryId() == null || move.getInventoryId() == 0) {
            inventory = inventoryMapper.findByMove(move);
        } else {
            inventory = inventoryMapper.findById(move.getInventoryId());
        }
        if (inventory == null) {
            Inventory newIty = new Inventory();
            mergeByMove(newIty, move);
            inventoryMapper.insert(newIty);
            return newIty;
        } else {
            mergeByMove(inventory, move);
            inventoryMapper.update(inventory);
            return inventory;
        }
    }

    /**
     * 根据 move 特性将需要的属性合并到 库存中
     *
     * @param inventory 库存信息
     * @param move      出入库信息
     * @return
     */
    public Inventory mergeByMove(Inventory inventory, Move move) {
        if (inventory.getId() == null || inventory.getId() == 0) {
            inventory.setMaterialId(move.getMaterialId());
            inventory.setQuantity(new BigDecimal(0));
            inventory.setCreateUidToLoginUser(userService);
            inventory.setWarehouseId(move.getWarehouseId());
            inventory.setReservoirId(move.getReservoirId());
            inventory.setStockId(move.getStockId());
        } else {
            inventory.setUpdateUidToLoginUser(userService);
            if (inventoryMapper.selectMoveCountByInventory(inventory) < 1) {
                inventory.setMaterialId(move.getMaterialId());
                inventory.setQuantity(new BigDecimal(0));
                inventory.setWarehouseId(move.getWarehouseId());
                inventory.setReservoirId(move.getReservoirId());
                inventory.setStockId(move.getStockId());
            }
        }
        return inventory;
    }

    /**
     * 根据 move 完成
     * 完成后增加仓库累计信息
     *
     * @param move
     */
    @Transactional(rollbackFor = Exception.class)
    public void finishByMove(Move move) {
        inventoryMapper.finishByMove(move, false);
        if (move.getType().equals(3)) {
            inventoryMapper.finishByMove(move, true);
        }
    }

}
