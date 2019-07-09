package com.lansea.wms;

import com.lansea.wms.enums.CompanyCategoryEnum;
import com.lansea.wms.util.EnumUtil;

public class Test {

    public static void main(String[] args) {
        CompanyCategoryEnum companyCategoryEnum = EnumUtil.valueOf(CompanyCategoryEnum.class, "供应商");
        System.out.println(companyCategoryEnum.getId());
        System.out.println(companyCategoryEnum.getValue());
        String s = "uG9u0Wy//nIkeB9WjgGZZW8A1Z1NBJMk3+GCjm+k7lrI6QKCwLvnq94Lbb/jWh4efF4tdngbzn0OfZpLBaMeNrRfFjHYieZiObPwpBoBGv18lIooz0ITKgWiJhTGCcIX";
        s = "VYcDU67ldIeQBA4QeRRHVIL1scIiCmFdQBExycOP6hfNxTP7M/U2mf3vO0pK3ShDCUcvmHxsXgqduJ1e5vmIMEfl1Lnrnia+e8I9LX6Yl/Eu9/1RL9DCmVc0qhmoLiIe";
    }
}
