package com.cateno.services;

import com.cateno.clients.DollarQuoteRestClient;
import com.cateno.forms.DollarQuoteForm;
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

    public DollarQuoteForm getDollarQuoteByDate(LocalDate date) {
        String formatted = this.toFormattedStringDate(date);
        return this.iDollarQuoteRestClient.getDollarQuoteByDate(formatted);
    }

    private String toFormattedStringDate(LocalDate localdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date = localdate.format(formatter);

        return "'" + date + "'";
    }
}
