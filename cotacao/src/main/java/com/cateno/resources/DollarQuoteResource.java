package com.cateno.resources;

import com.cateno.exceptions.TimeException;
import com.cateno.models.DollarQuote;
import com.cateno.services.DollarQuoteService;
import com.cateno.utils.DataUtils;
import com.cateno.views.DollarQuoteView;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/dollar")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DollarQuoteResource {

    @Inject
    DollarQuoteService iDollarQuoteService;

    @Inject
    DataUtils iDataUtils;

    /**
     * Busca a cotaçao do dolar na data de hoje.
     *
     * @return Model DollarQuote contendo as cotacoes de compra e vendo e data.
     * @throws TimeException Lança uma exceçao para datas no futuro ou antes de 10-01-1990.
     */
    @GET
    public ResponseEntity<DollarQuoteView> getQuoteToday() throws TimeException {
        DollarQuote quote =
                this.iDollarQuoteService.getDollarQuoteByDate(LocalDate.now());

        return ResponseEntity.ok(new DollarQuoteView(quote));
    }

    /**
     * Busca a cotacao do dolar em uma data especifica.
     *
     * @param date Uma String data no formato dd-MM-yyyy
     * @return Model DollarQuote contendo as cotacoes de compra e vendo e data.
     * @throws TimeException Lança uma exceçao para datas no futuro ou antes de 10-01-1990.
     */
    @GET
    @Path("/{date}")
    @Counted(name = "executions", description = "Conta Quantas Requisiçoes Foram Realizadas")
    @Timed(name = "timer", description = "Calcula o Tempo Decorrido em Cada Requisiçao", unit = MetricUnits.MILLISECONDS)
    public ResponseEntity<DollarQuoteView> getQuoteByDate(@PathParam("date") String date) throws TimeException {
        LocalDate localdate = iDataUtils.parse(date);

        DollarQuote quote =
                this.iDollarQuoteService
                        .getDollarQuoteByDate(localdate);

        return ResponseEntity.ok(new DollarQuoteView(quote));
    }
}
