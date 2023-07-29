package com.wms.config;

import com.wms.dto.ErrorDto;
import com.wms.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AuthenticationException exception) {
        return ResponseEntity.status(exception.getCode())
                .body(ErrorDto.builder().message(exception.getMessage()).build());
    }
}
