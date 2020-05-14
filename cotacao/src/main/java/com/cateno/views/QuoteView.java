package com.cateno.views;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDateTime;

@JsonbPropertyOrder(value = {"purchase", "sale", "datetime"})
public class QuoteView {

    private double purchase;

    private double sale;

    private LocalDateTime datetime;

    public QuoteView(double purchase, double sale, LocalDateTime datetime) {
        this.datetime = datetime;
        this.purchase = purchase;
        this.sale = sale;
    }

    @JsonbProperty("venda")
    public double getPurchase() {
        return purchase;
    }

    @JsonbProperty("compra")
    public double getSale() {
        return sale;
    }

    @JsonbProperty("data")
    public LocalDateTime getDatetime() {
        return datetime;
    }
}
