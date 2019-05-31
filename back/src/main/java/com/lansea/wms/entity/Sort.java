package com.lansea.wms.entity;

import java.io.Serializable;

/**
 * 排序信息
 */
public class Sort extends SerializeBase {

    private String sortField;
    private String sortOrder;


    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
