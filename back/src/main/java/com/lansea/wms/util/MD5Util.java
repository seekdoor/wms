package com.lansea.wms.util;

import com.lansea.wms.service.ParamsService;
import org.springframework.util.DigestUtils;

public class MD5Util {

    //盐，用于混交md5
    private static final String slat = BeanUtil.getBean(ParamsService.class).getMd5Secret();

    /**
     * 生成md5
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String base = str + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 验证 str 是否和 md5 一致
     *
     * @param str
     * @param md5
     * @return
     */
    public static boolean verify(String str, String md5) {
        return getMD5(str).equals(md5);
    }

    public static void main(String[] args) {
        String str = "123456";
        System.out.println(getMD5(str));
    }
}
