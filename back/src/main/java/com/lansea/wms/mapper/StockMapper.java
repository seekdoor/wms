package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StockMapper {

    Stock findById(@Param("id") Integer id);

    List<Stock> selectWhere(@Param("stock") Stock stock, @Param("sort") Sort sort);

    Integer update(@Param("stock") Stock stock);

    @Update("update stock set is_del = now() where id in (${ids})")
    Integer deleteByIds(@Param("ids") String ids);

    Integer insert(@Param("stock") Stock stock);

    List<Stock> selectAll();

    List<Stock> selectByReservoirId(@Param("reservoirId") Integer reservoirId);
}
