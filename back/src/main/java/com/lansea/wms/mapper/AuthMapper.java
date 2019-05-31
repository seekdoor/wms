package com.lansea.wms.mapper;

import com.lansea.wms.model.Auth;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthMapper {

    @Select("select * from auth where id=#{id}")
    Auth findById(@Param("id") Integer id);

    @Select("select * from auth")
    List<Auth> selectAll();

    @Select("select * from auth where name like '%${name}%'")
    List<Auth> selectByName(@Param("name") String name);

    Integer insert(@Param("auth") Auth auth);

    Integer update(@Param("auth") Auth auth);

    @Delete("delete from auth where id in(${ids})")
    Integer deleteByIds(@Param("ids") String ids);

}
