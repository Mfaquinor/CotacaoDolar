package com.cateno.views;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder(value = {"purchase", "sale"})
public class QuoteView {

    private double purchase;

    private double sale;

    public QuoteView(double purchase, double sale) {
        this.purchase = purchase;
        this.sale = sale;
    }

    @JsonbProperty("venda")
    public double getPurchase() {
        return this.purchase;
    }

    @JsonbProperty("compra")
    public double getSale() {
        return this.sale;
    }
}
