package com.cateno.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.DayOfWeek;
import java.time.LocalDate;

@ApplicationScoped
public class DataUtils {

    public LocalDate getLastBusinessDay(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        if(day == DayOfWeek.SATURDAY)
            return date.minusDays(1);
        if(day == DayOfWeek.SUNDAY)
            return date.minusDays(2);

        return date;
    }
}
