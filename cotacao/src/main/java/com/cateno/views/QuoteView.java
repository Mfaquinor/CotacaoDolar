package com.cateno.views;

public class QuoteView {

    private double purchase;

    private double sale;

    public QuoteView(double purchase, double sale) {
        this.purchase = purchase;
        this.sale = sale;
    }

    public double getPurchase() {
        return purchase;
    }

    public double getSale() {
        return sale;
    }
}
