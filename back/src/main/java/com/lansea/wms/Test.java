package com.lansea.wms;

import com.lansea.wms.enums.CompanyCategoryEnum;
import com.lansea.wms.util.EnumUtil;

public class Test {

    public static void main(String[] args) {
        CompanyCategoryEnum cc = EnumUtil.valueOf(CompanyCategoryEnum.class, 1);
        System.out.println( cc.getId() );
        System.out.println( cc.getValue() );
    }
}
