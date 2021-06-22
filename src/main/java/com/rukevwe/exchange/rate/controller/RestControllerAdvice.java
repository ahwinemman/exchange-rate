package com.rukevwe.exchange.rate.controller;

import com.rukevwe.exchange.rate.exception.InvalidRequestException;
import com.rukevwe.exchange.rate.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author oghenerukevwe
 */
@ControllerAdvice
@Slf4j
public class RestControllerAdvice {

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceResponse handleNotFoundException(InvalidRequestException invalidRequestException) {
        log.error("Invalid request was registered");
        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setSuccess(false);
        serviceResponse.setError(invalidRequestException.getMessage());
        return serviceResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ServiceResponse handleNotFoundException(Exception exception) {
        log.error("Server error was registered");
        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setSuccess(false);
        serviceResponse.setError(exception.getMessage());
        return serviceResponse;
    }
}
