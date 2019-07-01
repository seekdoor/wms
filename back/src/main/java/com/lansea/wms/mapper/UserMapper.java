package com.lansea.wms.mapper;

import com.lansea.wms.entity.Sort;
import com.lansea.wms.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id} limit 1")
    User findById(@Param("id") Integer id);

    @Select("select * from user where user_name = #{userName} and is_del is null limit 1")
    User findByUserName(@Param("userName") String userName);

    // 更新用户Token
    Integer refreshToken(@Param("user") User user);

    // 查询用户信息
    List<User> selectUserWhere(@Param("form") User user, @Param("sort") Sort sort);

    // 添加用户
    Integer insert(@Param("user") User user);

    // 编辑用户
    Integer update(@Param("user") User user);

    // 修改密码
    @Update("update user password = #{user.password} where id = #{user.id}")
    Integer changePassword(@Param("user") User user);

    // 批量删除
    @Update("update user set is_del = now() where id in (${ids})")
    Integer deleteByIds(@Param("ids") String ids);

    Integer logout(@Param("user") User user);

    Integer updatePassword(@Param("user") User user);

}
