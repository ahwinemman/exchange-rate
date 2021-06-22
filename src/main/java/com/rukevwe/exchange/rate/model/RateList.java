package com.rukevwe.exchange.rate.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class RateList extends BaseResponse{
    Map<String, BigDecimal> rateList;
}
