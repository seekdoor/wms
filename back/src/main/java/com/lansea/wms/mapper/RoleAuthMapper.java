package com.lansea.wms.mapper;

import com.lansea.wms.model.RoleAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleAuthMapper {

    @Select("select * from role_auth where id=#{id}")
    RoleAuth findByRoleId(@Param("roleId") Integer roleId);
}
