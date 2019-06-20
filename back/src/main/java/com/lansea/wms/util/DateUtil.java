package com.lansea.wms.util;

import ch.qos.logback.core.joran.action.TimestampAction;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_NO_LINE = "yyyyMMdd";

    public static String getDateTimeString(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format.format(timestamp.getTime());
    }

    public static String getDateTimeString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format.format(date.getTime());
    }

    public static String getDateString(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(timestamp.getTime());
    }

    public static String getDateString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date.getTime());
    }
}
