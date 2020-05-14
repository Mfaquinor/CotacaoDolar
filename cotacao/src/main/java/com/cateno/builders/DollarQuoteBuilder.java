package com.cateno.builders;

import com.cateno.forms.DollarQuoteForm;
import com.cateno.models.DollarQuote;

import java.sql.ConnectionBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DollarQuoteBuilder {

    private double quotePurchase;

    private double quoteSale;

    private LocalDate requestDate;

    private LocalDateTime quoteDate;

    public DollarQuoteBuilder from(DollarQuoteForm form) {
        this.quotePurchase = form.getQuotePurchase();
        this.quoteSale = form.getQuoteSale();
        this.quoteDate = form.getQuoteDate();

        return this;
    }

    public DollarQuoteBuilder with(LocalDate date) {
        this.requestDate = date;

        return this;
    }

    public DollarQuote build() {
        return new DollarQuote(this.quotePurchase, this.quoteSale, this.quoteDate, this.requestDate);
    }
}
