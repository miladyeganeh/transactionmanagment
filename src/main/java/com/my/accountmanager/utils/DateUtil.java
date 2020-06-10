package com.my.accountmanager.utils;

import java.time.LocalDate;
import java.util.Date;

public class DateUtil {

    public static long TRANSACTION_WINDOW_TIME = 180000L; //Millisecond - 3Minutes

    public static String getYearMonthDay() {
        return String.valueOf(LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth());
    }

    public static long timeDiffer(Date first, Date second) {
        return second.getTime() - first.getTime();
    }
}
