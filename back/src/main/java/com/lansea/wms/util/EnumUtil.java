package com.lansea.wms.util;

import java.lang.reflect.Method;

public class EnumUtil {
    /**
     * indexOf,传入的参数ordinal指的是需要的枚举值在设定的枚举类中的顺序，以0开始
     * T
     *
     * @param clazz
     * @param ordinal
     * @return
     * @author xiehao
     */
    public static <T extends Enum<T>> T indexOf(Class<T> clazz, int ordinal) {
        return (T) clazz.getEnumConstants()[ordinal];
    }

    /**
     * nameOf,传入的参数name指的是枚举值的名称，一般是大写加下划线的
     * T
     *
     * @param clazz
     * @param name
     * @return Enum T
     * @author xiehao
     */
    public static <T extends Enum<T>> T nameOf(Class<T> clazz, String name) {

        return (T) Enum.valueOf(clazz, name);
    }

    /**
     * 根据 id 获取
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T valueOf(Class<T> clazz, Integer id) {
        T[] arr = clazz.getEnumConstants();
        try {
            Method method = clazz.getDeclaredMethod("getId");
            for (T entity : arr) {
                Integer _id = (Integer) method.invoke(entity);
                if (_id.equals(id)) {
                    return entity;
                }
            }
        } catch (Exception ignore) {
        }
        return indexOf(clazz, 0);
    }

    /**
     * 根据 value 获取
     * @param clazz
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T valueOf(Class<T> clazz, String value) {
        T[] arr = clazz.getEnumConstants();
        try {
            Method method = clazz.getDeclaredMethod("getValue");
            for (T entity : arr) {
                String _value = (String) method.invoke(entity);
                if (_value.equals(value)) {
                    return entity;
                }
            }
        } catch (Exception ignore) {
        }
        return null;
    }

}
