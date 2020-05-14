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

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Deprecated
    protected DollarQuote() {}

    public DollarQuote(double quotePurchase, double quoteSale, LocalDate requestDate, LocalDateTime quoteDate) {
        this.quotePurchase = quotePurchase;
        this.quoteSale = quoteSale;
        this.requestDate = requestDate;
        this.quoteDate = quoteDate;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
