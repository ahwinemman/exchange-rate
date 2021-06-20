package com.rukevwe.exchange.rate.coinbase.models;

import lombok.Data;

import java.util.Map;

@Data
public class CBLatestRateResponse {
    private Time time;
    private String disclaimer;
    Map<String, Object> bpi;
}

