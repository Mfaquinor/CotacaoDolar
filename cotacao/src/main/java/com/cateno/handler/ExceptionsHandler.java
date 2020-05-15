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

    /**
     * Captura excecoes de datas no futuro ou no passado antes de 10-01-1990 nos resources
     *
     * @param exception
     * @return ExceptionView contendo a mensagem de erro.
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TimeException.class)
    public ExceptionView getTimeErrorException(TimeException exception) {
        return new ExceptionView(exception.getMessage());
    }

    /**
     * Captura excecoes de datas invalidas inseridas pelo usuario nos resources
     *
     * @param exception
     * @return ExceptionView contendo a mensagem de erro.
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ExceptionView getTimeErrorException(DateTimeParseException exception) {
        return new ExceptionView("Formato da data invalido. Use o formato dd-MM-yyyy!");
    }

    /**
     * Captura os erros internos disparados pela API.
     *
     * @param exception
     * @return ExceptionView contendo a mensagem de erro.
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionView getInternalErrorException(RuntimeException exception) {
        exception.printStackTrace();
        return new ExceptionView("Erro Interno!");
    }
}
