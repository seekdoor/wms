package com.lansea.wms.enums;

public enum CategoryTypeEnum {

    A(1, "单位类别"),
    B(2, "物料类别"),
    C(3, "入库单"),
    D(4, "出库单"),
    E(5, "设备分类"),
    F(6, "设备产权");

    private Integer id;
    private String value;

    CategoryTypeEnum(Integer id, String value) {
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
