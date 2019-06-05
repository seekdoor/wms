package com.lansea.wms.mapper;


import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.Depart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartMapper {

    Depart findById(@Param("id") Integer id);

    List<Depart> selectWhere(@Param("depart") Depart depart, @Param("sort") Sort sort);

    Integer update(@Param("depart") Depart depart);

    @Delete("delete from depart where id in (#{ids}")
    Integer deleteByIds(@Param("ids") String ids);

    Integer insert(@Param("depart") Depart depart);
}
