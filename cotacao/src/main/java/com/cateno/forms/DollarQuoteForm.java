package com.cateno.forms;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDateTime;
import java.util.List;

public class DollarQuoteForm {

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
        return this.value.get(0).getDate();
    }

    public static class DollarQuoteFormValue {

        @JsonbProperty("cotacaoCompra")
        private double purchase;

        @JsonbProperty("cotacaoVenda")
        private double sale;

        @JsonbProperty("dataHoraCotacao")
        @JsonbDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        private LocalDateTime date;

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

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }
}
