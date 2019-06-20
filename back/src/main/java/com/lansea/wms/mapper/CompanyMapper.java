package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {

    Company findById(@Param("id") Integer id);

    @Delete("delete from company where id in (${ids})")
    Integer deleteByIds(@Param("ids") String ids);

    Integer update(@Param("company") Company company);

    Integer insert(@Param("company") Company company);

    List<Company> selectWhere(@Param("company") Company company, @Param("sort") Sort sort);

    List<Company> selectByCategory(@Param("category") Integer category);

}
