package com.lansea.wms.mapper;

import com.lansea.wms.model.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UnitMapper {

    @Select("select * from unit where id = #{id}")
    Unit findById(@Param("id") Integer id);
}
