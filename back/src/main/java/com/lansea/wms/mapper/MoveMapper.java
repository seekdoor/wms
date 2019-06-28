package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.Move;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.model.StockTrans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MoveMapper {

    List<Move> selectWhere(@Param("move") Move move, @Param("sort") Sort sort);

    Move findById(@Param("id") Integer id);

    Integer insert(@Param("move") Move move);

    Integer update(@Param("move") Move move);

    @Update("update move set is_del = now() where id in (${ids}) and status = 1")
    Integer delete(@Param("ids") String ids);

    List<Move> selectByStockEntryId(@Param("stockEntryId") Integer stockEntryId);

    Integer updateStatusByStockEntry(@Param("stockEntry") StockEntry stockEntry);

    @Update("update move set is_del = now() where stock_entry_id in (${stockEntryIds}) and status = 1")
    Integer deleteByStockEntryIds(@Param("stockEntryIds") String stockEntryIds);

    Integer updateStatusByMove(@Param("move") Move move);

    List<Move> selectByStockTransId(@Param("stockTransId") Integer stockTransId);

    Integer updateStatusByStockTrans(@Param("stockTrans") StockTrans stockTrans);

    @Update("update move set is_del = now() where stock_entry_id in (${stockTransIds}) and status = 1")
    Integer deleteByStockTransIds(@Param("stockTransIds") String stockTransIds);

}
