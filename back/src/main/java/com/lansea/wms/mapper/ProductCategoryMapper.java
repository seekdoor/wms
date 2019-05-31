package com.lansea.wms.mapper;

import com.lansea.wms.model.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductCategoryMapper {

    @Select("select * from product_category where id = #{id}")
    ProductCategory findById(@Param("id") Integer id);

}
