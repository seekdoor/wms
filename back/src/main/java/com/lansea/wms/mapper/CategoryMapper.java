package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {

    Category findById(@Param("id") Integer id);

    List<Category> selectWhere(@Param("category") Category category, @Param("sort") Sort sort);

    List<Category> selectByType(@Param("type") Integer type);

    Integer update(@Param("category") Category category);

    Integer insert(@Param("category") Category category);

    @Update("update category set is_del = now() where id in (${ids})")
    Integer delete(@Param("ids") String ids);

}
