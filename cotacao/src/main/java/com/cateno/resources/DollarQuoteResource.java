package com.cateno.resources;

import com.cateno.exceptions.TimeException;
import com.cateno.models.DollarQuote;
import com.cateno.services.DollarQuoteService;
import com.cateno.utils.DataUtils;
import com.cateno.views.DollarQuoteView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;

@RestController
@RequestMapping("/dollar")
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
    @GetMapping
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
    @GetMapping("/{date}")
    public ResponseEntity<DollarQuoteView> getQuoteByDate(@PathVariable("date") String date) throws TimeException {
        LocalDate localdate = iDataUtils.parse(date);

        DollarQuote quote =
                this.iDollarQuoteService
                        .getDollarQuoteByDate(localdate);

        return ResponseEntity.ok(new DollarQuoteView(quote));
    }
}
