package com.rukevwe.exchange.rate.service;

import com.rukevwe.exchange.rate.model.ServiceResponse;

public interface RateService {

    ServiceResponse getLatestRate(String currency);

    ServiceResponse getRatesBetweenDates(String start, String end);

}
