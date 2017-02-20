package com.payroll.utils;
public class PrimitiveCompare {


    public static boolean isIntegerGreaterThanZero(Integer doubleValue) {
        return doubleValue != null && doubleValue > 0;
    }

    public static boolean isIntegerGreaterThanOrEqualToZero(Integer doubleValue) {
        return doubleValue != null && doubleValue >= 0;
    }

    public static boolean isDoubleNotEqualToNullOrZero(Double doubleValue) {
        return doubleValue != null && doubleValue != 0;
    }
    public static boolean isDoubleEqualToNullOrZero(Double doubleValue) {
        return doubleValue == null || doubleValue == 0;
    }
}
