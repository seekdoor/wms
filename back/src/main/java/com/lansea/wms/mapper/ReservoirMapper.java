package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.Reservoir;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReservoirMapper {

    Reservoir findById(@Param("id") Integer id);

    List<Reservoir> selectWhere(@Param("reservoir") Reservoir reservoir, @Param("sort") Sort sort);

    Integer update(@Param("reservoir") Reservoir reservoir);

    @Update("update reservoir set is_del = now() where id in (${ids})")
    Integer deleteByIds(@Param("ids") String ids);

    Integer insert(@Param("reservoir") Reservoir reservoir);

    List<Reservoir> selectAll();

    List<Reservoir> selectByWarehouseId(@Param("warehouseId") Integer warehouseId);
}
