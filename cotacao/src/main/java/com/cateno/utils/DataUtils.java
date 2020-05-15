package com.cateno.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class DataUtils {

    private static final String PATTERN = "dd-MM-yyyy";

    private LocalDate today = LocalDate.now();

    private LocalDate past = LocalDate.of(1985, Month.JANUARY, 10);

    public LocalDate parse(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDate.parse(date, formatter);
    }

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
