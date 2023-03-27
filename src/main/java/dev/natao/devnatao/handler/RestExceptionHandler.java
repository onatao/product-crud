package dev.natao.devnatao.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.natao.devnatao.models.error.ErrorMessage;
import dev.natao.devnatao.models.exception.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException (NotFoundException exception) {
        ErrorMessage error = new ErrorMessage("Not Found", exception.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
