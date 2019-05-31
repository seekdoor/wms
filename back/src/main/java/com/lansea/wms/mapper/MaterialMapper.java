package com.lansea.wms.mapper;

import com.lansea.wms.model.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MaterialMapper {

    @Select("select * from material where id=#{id}")
    Material findById(@Param("id") Integer id);

}
