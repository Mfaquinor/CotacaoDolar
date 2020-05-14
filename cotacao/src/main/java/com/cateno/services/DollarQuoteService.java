package com.cateno.services;

import com.cateno.builders.DollarQuoteBuilder;
import com.cateno.clients.DollarQuoteRestClient;
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

    public DollarQuote getDollarQuoteByDate(LocalDate localdate) {
        LocalDate date = this.iDataUtils.getLastBusinessDay(localdate);
        String formatted = this.toFormattedStringDate(date);
        DollarQuoteForm form = this.iDollarQuoteRestClient.getDollarQuoteByDate(formatted);

        DollarQuote quote =
                new DollarQuoteBuilder()
                        .from(form)
                        .with(localdate)
                        .build();

        this.iDollarQuoteRepository.save(quote);

        return quote;
    }

    private String toFormattedStringDate(LocalDate localdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date = localdate.format(formatter);

        return "'" + date + "'";
    }
}
