package com.lansea.wms.mapper;

import com.lansea.wms.model.RoleAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleAuthMapper {

    @Select("select * from role_auth where id=#{id}")
    RoleAuth findByRoleId(@Param("roleId") Integer roleId);

    @Select("delete from role_auth where role_id in (${roleIds})")
    Integer deleteByRoleIds(@Param("roleIds") String roleIds);

    @Select("delete from role_auth where auth_id in (${authIds})")
    Integer deleteByAuthIds(@Param("authIds") String authIds);

    Integer insertAll(@Param("list") List<RoleAuth> list);
}
