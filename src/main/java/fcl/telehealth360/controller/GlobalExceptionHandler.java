package fcl.telehealth360.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import fcl.telehealth360.dto.response.Response;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Response> handleException(DuplicateKeyException ex) {
        String exception = ex.getMessage();
        List<String> errors = new ArrayList<>();
        errors.add(exception);
        Response response = Response.builder()
                            .timeStamp(new Date())
                            .responseMessage(exception)
                            .responseCode(HttpStatus.CONFLICT.toString())
                            .data(null)
                            .errors(errors)
                            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);    
    }
}
