package com.cateno.handler;

import com.cateno.exceptions.TimeException;
import com.cateno.views.ExceptionView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TimeException.class)
    public ExceptionView getTimeErrorException(TimeException exception) {
        return new ExceptionView(exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ExceptionView getTimeErrorException(DateTimeParseException exception) {
        return new ExceptionView("Formato da data invalido. Use o formato dd-MM-yyyy!");
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionView getInternalErrorException(RuntimeException exception) {
        exception.printStackTrace();
        return new ExceptionView("Erro Interno!");
    }
}
