package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.StockTrans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StockTransMapper {

    List<StockTrans> selectWhere(@Param("form") StockTrans form, @Param("sort") Sort sort);

    @Update("update stock_trans set is_del = now() where id in (${ids}) and status = 1")
    Integer delete(@Param("ids") String ids);

    StockTrans findById(@Param("id") Integer id);

    Integer insert(@Param("form") StockTrans form);

    Integer update(@Param("form") StockTrans form);

    Integer updateStatus(@Param("form") StockTrans form);
}
