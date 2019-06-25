package com.lansea.wms.service;

import com.lansea.wms.mapper.NumberCreateMapper;
import com.lansea.wms.model.NumberCreate;
import com.lansea.wms.util.DateUtil;
import com.lansea.wms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NumberCreateService {

    @Autowired
    NumberCreateMapper numberCreateMapper;

    /**
     * 生成单号信息
     *
     * @param type 单号类别
     * @return String
     */
    synchronized public String create(String type) {
        NumberCreate numberCreate = numberCreateMapper.findByType(type);
        if (numberCreate == null) {
            return null;
        }
        Date now = new Date();
        String dbLastDate = DateUtil.getDateString(numberCreate.getLastDate());
        String nowLastDate = DateUtil.getDateString(now);
        Integer index;
        if (dbLastDate.equals(nowLastDate)) {
            index = numberCreate.getIndex();
            numberCreate.setIndex(numberCreate.getIndex() + 1);
        } else {
            index = 1;
            numberCreate.setLastDate(now);
            numberCreate.setIndex(1);
        }
        numberCreateMapper.update(numberCreate);
        StringBuilder number = new StringBuilder(numberCreate.getPrefix());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        number.append(sdf.format(numberCreate.getLastDate()));
        number.append(StringUtil.startPad(String.valueOf(index), "0", 4));
        return number.toString();
    }

}
