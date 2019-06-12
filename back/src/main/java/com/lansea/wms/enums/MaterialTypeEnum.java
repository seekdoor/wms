package com.lansea.wms.enums;

public enum MaterialTypeEnum {

    A(1, "采购物料"),
    B(2, "生产产品"),
    C(3, "物料和产品");

    private Integer id;
    private String value;

    MaterialTypeEnum(Integer id, String value) {
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
