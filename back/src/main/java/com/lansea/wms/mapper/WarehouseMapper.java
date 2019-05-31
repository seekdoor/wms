package com.lansea.wms.mapper;

import com.lansea.wms.model.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WarehouseMapper {

    @Select("select * from warehouse where id = #{id}")
    Warehouse findById(@Param("id") Integer id);
}
