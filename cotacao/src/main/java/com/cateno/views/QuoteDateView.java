package com.cateno.views;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonbPropertyOrder(value = {"requested", "valid", "schedule"})
public class QuoteDateView {

    private LocalDate requested;

    private LocalDate valid;

    private LocalTime schedule;

    public QuoteDateView(LocalDate requested, LocalDate valid, LocalTime schedule) {
        this.requested = requested;
        this.schedule = schedule;
        this.valid = valid;
    }

    @JsonbProperty("requisitada")
    @JsonbDateFormat("dd-MM-yyyy")
    public LocalDate getRequested() {
        return requested;
    }

    @JsonbProperty("valida")
    @JsonbDateFormat("dd-MM-yyyy")
    public LocalDate getValid() {
        return valid;
    }

    @JsonbProperty("horario")
    @JsonbDateFormat("HH:mm:ss")
    public LocalTime getSchedule() {
        return schedule;
    }
}
