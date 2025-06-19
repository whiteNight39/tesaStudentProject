//package com.example.tesastudentfredrick;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.*;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
////    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request
//    ) {
//        List<Map<String, String>> errors = new ArrayList<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            Map<String, String> err = new HashMap<>();
//            err.put("field", error.getField());
//            err.put("message", error.getDefaultMessage());
//            errors.add(err);
//        });
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("timestamp", new Date());
//        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
//        responseBody.put("errors", errors);
//
//        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
//    }
//}
