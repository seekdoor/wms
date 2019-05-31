package com.lansea.wms.mapper;

import com.lansea.wms.model.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Select("select * from auth where id=#{id}")
    Auth findById(@Param("id") Integer id);

}
