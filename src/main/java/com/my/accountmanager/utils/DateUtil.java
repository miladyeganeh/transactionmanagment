package com.my.accountmanager.utils;

import java.time.LocalDate;

public class DateUtil {
    public static String getYearMonthDay() {
        return String.valueOf(LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth());
    }
}
