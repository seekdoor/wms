package com.lansea.wms.mapper;

import com.lansea.wms.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {

    @Select("select * from role where id=#{id}")
    Role findById(@Param("id") Integer id);

}
