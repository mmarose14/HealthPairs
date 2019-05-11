package com.healthpairs.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtility {

    public static boolean isStringNullOrEmpty(String value) {
        return StringUtils.isBlank(value);
    }

    public static boolean isStringNotNullOrEmpty(String value) {
        return StringUtils.isNotBlank(value);
    }
}
