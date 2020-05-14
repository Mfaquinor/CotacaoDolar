package com.cateno.resources;

import com.cateno.forms.DollarQuoteForm;
import com.cateno.models.DollarQuote;
import com.cateno.services.DollarQuoteService;
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

    @GetMapping
    public ResponseEntity<DollarQuote> getQuoteToday() {
        return ResponseEntity.ok(this.iDollarQuoteService.getDollarQuoteByDate(LocalDate.now()));
    }

    @GetMapping("/{date}")
    public ResponseEntity<DollarQuote> getQuoteByDate(@PathVariable String date) {
        LocalDate localdate = LocalDate.parse(date);
        DollarQuote quote = this.iDollarQuoteService.getDollarQuoteByDate(localdate);
        return ResponseEntity.ok(quote);
    }
}
