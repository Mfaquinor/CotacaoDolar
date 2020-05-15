package com.cateno.forms;

import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DollarQuoteForm {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<DollarQuoteFormValue> value;

    public List<DollarQuoteFormValue> getValue() {
        return value;
    }

    public void setValue(List<DollarQuoteFormValue> value) {
        this.value = value;
    }

    public double getQuotePurchase() {
        return this.value.get(0).getPurchase();
    }

    public double getQuoteSale() {
        return this.value.get(0).getSale();
    }

    public LocalDateTime getQuoteDate() {
        String date = this.value.get(0).getDate();
        return LocalDateTime.parse(date, formatter);
    }

    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    public static class DollarQuoteFormValue {

        @JsonbProperty("cotacaoCompra")
        private double purchase;

        @JsonbProperty("cotacaoVenda")
        private double sale;

        @JsonbProperty("dataHoraCotacao")
        private String date;

        public double getPurchase() {
            return purchase;
        }

        public void setPurchase(double purchase) {
            this.purchase = purchase;
        }

        public double getSale() {
            return sale;
        }

        public void setSale(double sale) {
            this.sale = sale;
        }

        public String getDate() {
            return this.date.split("\\.")[0];
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
