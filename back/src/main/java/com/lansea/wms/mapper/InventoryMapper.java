package com.lansea.wms.mapper;

import com.lansea.wms.model.Inventory;
import com.lansea.wms.model.Move;
import com.lansea.wms.model.StockEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InventoryMapper {

    List<Inventory> selectWhere(@Param("form") Inventory form);

    Inventory findById(@Param("id") Integer id);

    Integer insert(@Param("form") Inventory form);

    Integer update(@Param("form") Inventory form);

    @Update("update inventory set is_del = now() where id in (${ids})")
    Integer delete(@Param("ids") String ids);

    Inventory findByMove(@Param("move") Move move);

    Integer selectMoveCountByInventory(@Param("inventory") Inventory inventory);

    Integer finishByMove(@Param("move") Move move, @Param("isTid") Boolean isTid);

}
