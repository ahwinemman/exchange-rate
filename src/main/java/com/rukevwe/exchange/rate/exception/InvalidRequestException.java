package com.rukevwe.exchange.rate.exception;

public class InvalidRequestException extends BaseException {
    public InvalidRequestException(String message) {
        super(message + " You can check the swagger documentation for expected format");
    }
}
