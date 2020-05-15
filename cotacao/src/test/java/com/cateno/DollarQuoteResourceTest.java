package com.cateno;

import com.cateno.exceptions.TimeException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class DollarQuoteResourceTest {

    private static final String URL = "/dollar/";

    private static final String QUOTE_SALE = "cotacao.venda";
    private static final String QUOTE_PURCHASE = "cotacao.compra";

    private static final String DATE_REQUESTED = "data.requisitada";
    private static final String DATE_VALID = "data.valida";

    @Test
    public void testWhenValidDateExpectValidDollarQuote() {
        given().when()
                .get(URL + "14-05-2020")
                .then()
                    .statusCode(200)
                    .body(QUOTE_SALE, is(5.9366f),
                        QUOTE_PURCHASE, is(5.9372f),
                        DATE_REQUESTED, is("14-05-2020"),
                        DATE_VALID, is("14-05-2020"));
    }

    @Test
    public void testWhenIsSaturdayDateExpectValidFridayDollarQuote() {
        given().when()
                .get(URL + "09-05-2020")
                .then()
                    .statusCode(200)
                    .body(QUOTE_SALE, is(5.7647f),
                        QUOTE_PURCHASE, is(5.7653f),
                        DATE_REQUESTED, is("09-05-2020"),
                        DATE_VALID, is("08-05-2020"));
    }

    @Test
    public void testWhenIsSundayDateExpectValidFridayDollarQuote() {
        given().when()
                .get(URL + "10-05-2020")
                .then()
                    .statusCode(200)
                    .body(QUOTE_SALE, is(5.7647f),
                        QUOTE_PURCHASE, is(5.7653f),
                        DATE_REQUESTED, is("10-05-2020"),
                        DATE_VALID, is("08-05-2020"));
    }

    @Test
    public void testWhenIsHollidaysDateExpectValidLastBusinnesDollarQuote() {
        given().when()
                .get(URL + "25-12-2019")
                .then()
                    .statusCode(200)
                    .body(QUOTE_SALE, is(4.0793f),
                        QUOTE_PURCHASE, is(4.0813f),
                        DATE_REQUESTED, is("25-12-2019"),
                        DATE_VALID, is("24-12-2019"));
    }

    @Test
    public void testWhenIsCarnivalDateExpectValidLastBusinnesDollarQuote() {
        given().when()
                .get(URL + "25-02-2020")
                .then()
                    .statusCode(200)
                    .body(QUOTE_SALE, is(4.3918f),
                        QUOTE_PURCHASE, is(4.3924f),
                        DATE_REQUESTED, is("25-02-2020"),
                        DATE_VALID, is("21-02-2020"));
    }

    @Test
    public void testWhenIsNewYearDateExpectValidLastBusinnesDollarQuote() {
        given().when()
                .get(URL + "01-01-2020")
                .then()
                    .statusCode(200)
                    .body(QUOTE_SALE, is(4.0301f),
                        QUOTE_PURCHASE, is(4.0307f),
                        DATE_REQUESTED, is("01-01-2020"),
                        DATE_VALID, is("31-12-2019"));
    }

    @Test
    public void testWhenIsTodayExpectValidDollarQuote() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate now = LocalDate.now();

        given().when()
                .get(URL + now.format(formatter))
                .then()
                    .statusCode(200);
    }

    @Test
    public void testWhenIsFutureDateExpectError() {
        given().when()
                .get(URL + "01-01-2200")
                .then()
                .statusCode(400);
    }

    @Test
    public void testWhenIsPastLimitDateExpectError() {
        given().when()
                .get(URL + "10-10-1980")
                .then()
                .statusCode(400);
    }

    @Test
    public void testWhenIsInvalidDateExpectError() {
        given().when()
                .get(URL + "32-01-2020")
                .then()
                .statusCode(400);
    }
}
