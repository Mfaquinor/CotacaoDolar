package com.cateno.views;

import com.cateno.models.DollarQuote;

import javax.json.bind.annotation.JsonbProperty;

public class DollarQuoteView {

    private QuoteView quote;

    public DollarQuoteView(DollarQuote model) {
        this.quote = new QuoteView(model.getQuotePurchase(), model.getQuoteSale(), model.getQuoteDate());
    }

    @JsonbProperty("cotacao")
    public QuoteView getQuote() {
        return quote;
    }
}
