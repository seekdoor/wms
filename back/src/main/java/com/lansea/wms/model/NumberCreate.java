package com.lansea.wms.model;

import com.lansea.wms.entity.SerializeBase;
import com.lansea.wms.util.DateUtil;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@ApiModel(value = "NumberCreate", description = "单号生成")
public class NumberCreate extends SerializeBase {

    private Integer id;
    private String type;
    private String prefix;
    private String remark;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date lastDate;
    private Integer index;

    @DateTimeFormat(pattern = DateUtil.DATE_TIME_FORMAT)
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
