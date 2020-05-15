package com.cateno.services;

import com.cateno.builders.DollarQuoteBuilder;
import com.cateno.clients.DollarQuoteRestClient;
import com.cateno.exceptions.TimeException;
import com.cateno.forms.DollarQuoteForm;
import com.cateno.models.DollarQuote;
import com.cateno.repositories.DollarQuoteRepository;
import com.cateno.utils.DataUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DollarQuoteService {

    @Inject
    @RestClient
    DollarQuoteRestClient iDollarQuoteRestClient;

    @Inject
    DollarQuoteRepository iDollarQuoteRepository;

    @Inject
    DataUtils iDataUtils;

    /**
     * Busca a cotaçao do dolar de compra e venda para uma data especifica.
     *
     * @param localdate
     * @return Model DollarQuote contendo as cotacoes de compra e vendo e data.
     * @throws TimeException Lança uma exceçao para datas no futuro ou antes de 10-01-1990.
     */
    public DollarQuote getDollarQuoteByDate(LocalDate localdate) throws TimeException {
        DollarQuoteForm form = this.doRequestDollarQuoteByDateEscapingHollidays(localdate);

        DollarQuote quote =
                new DollarQuoteBuilder()
                        .from(form)
                        .with(localdate)
                        .build();

        this.iDollarQuoteRepository.save(quote);

        return quote;
    }

    /**
     * Procura a cotacao do ultimo dia util quando a data atual caiu em um feriado.
     *
     * @param localdate
     * @return Model DollarQuoteForm contendo o json retornado pela API.
     * @throws TimeException Lança uma exceçao para datas no futuro ou antes de 10-01-1990.
     */
    private DollarQuoteForm doRequestDollarQuoteByDateEscapingHollidays(LocalDate localdate) throws TimeException {
        DollarQuoteForm form = this.doRequestDollarQuoteByDate(localdate);

        if(form.isEmpty()) {
            form = this.doRequestDollarQuoteByDateEscapingHollidays(localdate.minusDays(1));
        }

        return form;
    }

    /**
     * Dispara a requisicao para a API Rest de cotacao de moedas.
     *
     * @param localdate
     * @return Model DollarQuoteForm contendo o json retornado pela API.
     * @throws TimeException Lança uma exceçao para datas no futuro ou antes de 10-01-1990.
     */
    private DollarQuoteForm doRequestDollarQuoteByDate(LocalDate localdate) throws TimeException {
        LocalDate date = this.getValidDate(localdate);
        String formatted = this.toFormattedStringDate(date);
        return this.iDollarQuoteRestClient.getDollarQuoteByDate(formatted);
    }

    /**
     * Devolve uma data em um dia util caso a data inserida seja um final de semana.
     * Valida se a data nao esta no futuro ou em um passado antes de 10-01-1890 lancando uma excecao.
     *
     * @param localdate
     * @return LocalDate data do ultimo dia util.
     * @throws TimeException Lança uma exceçao para datas no futuro ou antes de 10-01-1990.
     */
    private LocalDate getValidDate(LocalDate localdate) throws TimeException {
        if(this.iDataUtils.isDateInFuture(localdate))
            throw new TimeException("Data no futuro é invalida");
        if(this.iDataUtils.isDateInLimitPast(localdate))
            throw new TimeException("Datas antes de 10/01/1985 são invalidas");

        return this.iDataUtils.getLastBusinessDay(localdate);
    }

    /**
     * Formata uma data para o padrao reconehcido pela API de cotacao de moedas MM-dd-yyyy
     *
     * @param localdate
     * @return Uma string de data no formato MM-dd-yyyy
     */
    private String toFormattedStringDate(LocalDate localdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date = localdate.format(formatter);

        return "'" + date + "'";
    }
}
