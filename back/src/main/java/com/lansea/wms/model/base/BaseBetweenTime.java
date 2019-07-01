package com.lansea.wms.model.base;

import com.lansea.wms.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseBetweenTime {

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date startTime;

    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
