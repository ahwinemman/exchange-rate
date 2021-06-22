package com.rukevwe.exchange.rate.provider.coindesk.models;

import lombok.Data;

import java.util.Map;

@Data
public class RateResponse<T> {
    private Time time;
    private String disclaimer;
    Map<String, T> bpi;
}

