package com.lansea.wms;

import com.lansea.wms.enums.CompanyCategoryEnum;
import com.lansea.wms.util.EnumUtil;

public class Test {

    public static void main(String[] args) {
        CompanyCategoryEnum companyCategoryEnum = EnumUtil.valueOf(CompanyCategoryEnum.class, "供应商");
        System.out.println(companyCategoryEnum.getId());
        System.out.println(companyCategoryEnum.getValue());
    }
}
