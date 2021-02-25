package spring.dynamictest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import spring.exception.PostNotFoundException;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Void> postNotFoundExceptionHandler() {
        return ResponseEntity.badRequest().build();
    }
}
