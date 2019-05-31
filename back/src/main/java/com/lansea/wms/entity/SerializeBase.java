package com.lansea.wms.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class SerializeBase implements Serializable {

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
