package com.lansea.wms.util;

public class SQLUtil {

    /**
     * 返回删除sql语句
     *
     * @param tableName 表名
     * @param ids       id列表
     * @return
     */
    public static String getSoftDeleteByIdsSql(String tableName, String ids) {
        return String.format("update %s set is_del = null where id in (#{%s})", tableName, ids);
    }
}
