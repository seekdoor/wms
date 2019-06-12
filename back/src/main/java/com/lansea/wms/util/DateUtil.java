package com.lansea.wms.util;

import ch.qos.logback.core.joran.action.TimestampAction;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getDateTimeString(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(timestamp.getTime());
    }

    public static String getDateTimeString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date.getTime());
    }
}
