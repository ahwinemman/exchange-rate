package com.rukevwe.exchange.rate.model;

import lombok.Data;

@Data
public class ServiceResponse<T> {
    private boolean success;
    private String error;
    private String message;
    private T data;
}
