package com.rukevwe.exchange.rate.coinbase.models;

import lombok.Data;

import java.util.Map;

@Data
public class CBRateHistoryResponse {
    private Map<String, Object> bpi;
    private String disclaimer;
    private Time time;
}
