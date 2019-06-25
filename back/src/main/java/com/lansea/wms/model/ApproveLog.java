package com.lansea.wms.model;

import com.lansea.wms.model.base.BaseUser;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Repository;

@Repository
@ApiModel(value = "ApproveLog", description = "审批日志")
public class ApproveLog extends BaseUser {

    private Integer id;
    private Integer pid;
    private Integer type;
    private Integer status;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
