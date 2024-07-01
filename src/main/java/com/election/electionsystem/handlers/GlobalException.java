package com.election.electionsystem.handlers;


import com.election.electionsystem.exceptions.ElectionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalException {
@ExceptionHandler(ElectionException.class)
    @ResponseBody
    public ResponseEntity<?> handleError(ElectionException exception){
        return ResponseEntity.status(BAD_REQUEST)
                .body(Map.of("error", exception.getMessage(),"success",false));
}
}
