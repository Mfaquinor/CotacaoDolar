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

    private LocalDate past = LocalDate.of(1990, Month.JANUARY, 10);

    /**
     * Converte uma String no formato dd-MM-yyyy para um objeto LocalDate.
     *
     * @param date String no formato dd-MM-yyyy.
     * @return LocalDate.
     */
    public LocalDate parse(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Retorna a data do ultimo dia util.
     *
     * @param date Data atual.
     * @return LocalDate do ultimo dia util.
     */
    public LocalDate getLastBusinessDay(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        if(day == DayOfWeek.SATURDAY)
            return date.minusDays(1);
        if(day == DayOfWeek.SUNDAY)
            return date.minusDays(2);

        return date;
    }

    /**
     * Verifica se a data esta no futuro.
     *
     * @param date
     * @return
     */
    public boolean isDateInFuture(LocalDate date) {
        return this.today.isBefore(date);
    }

    /**
     * Verifica se a data esta antes de 10-01-1990.
     *
     * @param date
     * @return
     */
    public boolean isDateInLimitPast(LocalDate date) {
        return this.past.isAfter(date);
    }
}
