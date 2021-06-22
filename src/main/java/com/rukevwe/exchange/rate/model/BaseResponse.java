package com.rukevwe.exchange.rate.model;

import lombok.Data;

@Data
public class BaseResponse {
    private String currency;
    private String lastUpdatedTime;
}
