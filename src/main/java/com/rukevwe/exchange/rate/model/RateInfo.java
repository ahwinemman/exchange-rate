package com.rukevwe.exchange.rate.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateInfo extends BaseResponse {
    private BigDecimal rate;
    private String description;
}

