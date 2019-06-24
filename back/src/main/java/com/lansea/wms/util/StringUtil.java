package com.lansea.wms.util;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class StringUtil {

    /**
     * 字符串补全
     * 当字符串长度低于 len 长度时
     * 向左右两侧补全给定字符 pad
     *
     * @param str    原字符串
     * @param pad    补全字符
     * @param len    指定长度
     * @param isLeft 是否为左侧
     * @return
     */
    private static String pad(String str, String pad, Integer len, Boolean isLeft) {
        if (str.length() >= len) {
            return str;
        }
        StringBuilder ret = new StringBuilder();
        if (!isLeft) ret.append(str);
        for (int i = 0; i < len - str.length(); i++) {
            ret.append(pad);
        }
        if (isLeft) ret.append(str);
        return ret.toString();
    }

    /**
     * 左侧补全
     *
     * @param str 原字符串
     * @param pad 补全字符
     * @param len 指定长度
     * @return
     */
    public static String startPad(String str, String pad, Integer len) {
        return pad(str, pad, len, true);
    }

    /**
     * 右侧补全
     *
     * @param str 原字符串
     * @param pad 补全字符
     * @param len 指定长度
     * @return
     */
    public static String endPad(String str, String pad, Integer len) {
        return pad(str, pad, len, false);
    }

    /**
     * 检查字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.equals("");
    }

}
