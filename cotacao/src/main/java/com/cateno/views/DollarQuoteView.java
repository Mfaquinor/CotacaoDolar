package com.cateno.views;

import com.cateno.models.DollarQuote;

import javax.json.bind.annotation.JsonbProperty;

public class DollarQuoteView {

    private QuoteView quote;

    private QuoteDateView date;

    public DollarQuoteView(DollarQuote model) {
        this.quote = new QuoteView(
                model.getQuotePurchase(),
                model.getQuoteSale()
        );

        this.date = new QuoteDateView(
                model.getRequestDate(),
                model.getQuoteDate().toLocalDate(),
                model.getQuoteDate().toLocalTime()
        );
    }

    @JsonbProperty("cotacao")
    public QuoteView getQuote() {
        return quote;
    }

    @JsonbProperty("data")
    public QuoteDateView getDate() {
        return date;
    }
}
