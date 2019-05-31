package com.lansea.wms.mapper;

import com.lansea.wms.model.MaterialCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MaterialCategoryMapper {

    @Select("select * from material_category where id = #{id}")
    MaterialCategory findById(@Param("id") Integer id);
}
