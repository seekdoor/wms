package com.lansea.wms.util;

public class Regex {
    public static final String MOBILE = "^1[345789]\\d{9}$";
    public static final String USER_NAME = "^[a-zA-Z]{1}[a-zA-Z0-9_]{2,40}$";
    public static final String REAL_NAME = "^.{2,20}$";
    public static final String PASSWORD = "^.{6,20}$";
    public static final String EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
}
