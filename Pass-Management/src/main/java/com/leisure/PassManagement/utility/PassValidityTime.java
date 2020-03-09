package com.leisure.PassManagement.utility;


import java.time.*;


public class PassValidityTime {

    public static LocalDateTime passValidFor() {

        LocalDateTime passValidTill = LocalDateTime.now()
                .with(LocalTime.MAX);
        return passValidTill;

    }

    public static LocalDateTime renewPassForAnExtraDay() {

        LocalDateTime passValidTill = LocalDateTime.now()
                .with(LocalTime.MAX).plusDays(1);
        return passValidTill;

    }

}
