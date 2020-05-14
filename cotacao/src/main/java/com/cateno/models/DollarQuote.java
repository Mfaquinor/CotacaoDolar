package com.cateno.models;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DollarQuote extends Model {

    private double quotePurchase;

    private double quoteSale;

    private LocalDate requestDate;

    private LocalDateTime quoteDate;

    @Deprecated
    protected DollarQuote() {}

    public DollarQuote(double quotePurchase, double quoteSale, LocalDateTime quoteDate, LocalDate requestDate) {
        this.quotePurchase = quotePurchase;
        this.quoteSale = quoteSale;
        this.quoteDate = quoteDate;
        this.requestDate = requestDate;
    }

    public double getQuotePurchase() {
        return quotePurchase;
    }

    public void setQuotePurchase(double quotePurchase) {
        this.quotePurchase = quotePurchase;
    }

    public double getQuoteSale() {
        return quoteSale;
    }

    public void setQuoteSale(double quoteSale) {
        this.quoteSale = quoteSale;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDateTime quoteDate) {
        this.quoteDate = quoteDate;
    }
}
