package com.cateno.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

@ApplicationScoped
public class DataUtils {

    private LocalDate today = LocalDate.now();

    private LocalDate past = LocalDate.of(1984, Month.NOVEMBER, 28);

    public LocalDate getLastBusinessDay(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        if(day == DayOfWeek.SATURDAY)
            return date.minusDays(1);
        if(day == DayOfWeek.SUNDAY)
            return date.minusDays(2);

        return date;
    }

    public boolean isDateInFuture(LocalDate date) {
        return this.today.isBefore(date);
    }

    public boolean isDateInLimitPast(LocalDate date) {
        return this.past.isAfter(date);
    }
}
