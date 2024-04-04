package com.s1410.calme.Infrastructure.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    //Maneja las expeciocnes que corresponden con el error 404 Not Found
    //Para Agregar una expeción a ser manejada así solo deben colocarla dentro de las llaves
    //En caso de que hagan una expeción propia, solo necesita que haga "extends" de RuntimeException
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundBusinessException(RuntimeException exception) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }


    //TODO: Manejar Binding Result Exceptions

    //Maneja cualquier otra excepción que no haya sido considerada en los casos anteriores
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllOtherExceptions(Exception exception) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiException apiException = new ApiException(
                exception.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, internalServerError);
    };
}
