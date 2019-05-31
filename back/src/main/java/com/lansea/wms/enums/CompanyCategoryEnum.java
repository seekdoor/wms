package com.lansea.wms.enums;

public enum CompanyCategoryEnum {

    SUPPLIER(1, "供应商"),
    CLIENT(2, "客户"),
    CARRIER(3, "承运商");

    private Integer id;
    private String value;

    CompanyCategoryEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}

