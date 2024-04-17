package com.s1410.calme.Infrastructure.Exceptions;
import com.s1410.calme.Infrastructure.Exceptions.custom.AppointmentAvailabilityException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    //Maneja las expeciocnes que corresponden con el error 404 Not Found
    //Para Agregar una expeción a ser manejada así solo deben colocarla dentro de las llaves
    //En caso de que hagan una expeción propia, solo necesita que haga "extends" de RuntimeException
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException exception) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {EntityExistsException.class})
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException exception) {

        HttpStatus entityExists = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                entityExists,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, entityExists);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleWrongArgumentException(IllegalArgumentException exception) {

        HttpStatus wrongArgument = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                wrongArgument,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, wrongArgument);
    }


    @ExceptionHandler(value = BindingResultException.class)
    public ResponseEntity<Object> handleBindingResultException(BindingResultException exception){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));

        BindingResultErrorDetails apiException = new BindingResultErrorDetails(
                errorMessages,
                badRequest,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = AppointmentAvailabilityException.class)
    public ResponseEntity<Object> handleBadRequestExceptions(Exception exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    };


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
