package com.lansea.wms.mapper;

import com.lansea.wms.model.Move;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MoveMapper {

    List<Move> selectWhere(@Param("move") Move move);

    Move findById(@Param("id") Integer id);

    Integer insert(@Param("move") Move move);

    Integer update(@Param("move") Move move);

    @Update("update move set is_del = now() where id in (${ids})")
    Integer delete(@Param("ids") String ids);

    List<Move> selectByStockEntryId(@Param("stockEntryId") Integer stockEntryId);

}
