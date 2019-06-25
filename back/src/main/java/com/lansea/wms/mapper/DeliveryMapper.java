package com.lansea.wms.mapper;


import com.lansea.wms.model.Delivery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    List<Delivery> selectWhere(@Param("form") Delivery form);

    Integer insert(@Param("form") Delivery form);

    Integer update(@Param("form") Delivery form);

    @Delete("update delivery set is_del = now() where id in (${ids})")
    Integer delete(@Param("ids") String ids);

    Delivery findById(@Param("id") Integer id);

}
