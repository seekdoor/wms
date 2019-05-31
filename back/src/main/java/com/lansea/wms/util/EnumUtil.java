package com.lansea.wms.util;

public class EnumUtil {
    /**
     * 根据索引获取
     *
     * @param <T>     枚举类型
     * @param clazz   枚举类型
     * @param ordinal 索引值
     * @return
     */
    public static <T extends Enum<T>> T valueOf(Class<T> clazz, int ordinal) {
        return (T) clazz.getEnumConstants()[ordinal];
    }

    /**
     * 根据name获取
     *
     * @param <T>      枚举类型
     * @param enumType 枚举类型
     * @param name     名称
     * @return
     */
    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        return (T) Enum.valueOf(enumType, name);
    }
}
