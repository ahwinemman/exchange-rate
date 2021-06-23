package com.rukevwe.exchange.rate.exception;

public class ProviderException extends BaseException {
    public ProviderException() {
        super("Server error returned from rate provider");
    }
}

