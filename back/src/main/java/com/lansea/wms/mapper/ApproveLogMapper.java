package com.lansea.wms.mapper;

import com.lansea.wms.model.ApproveLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApproveLogMapper {

    Integer insert(@Param("approveLog") ApproveLog approveLog);

}
