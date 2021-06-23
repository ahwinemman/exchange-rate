package com.rukevwe.exchange.rate.service;

import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
import com.rukevwe.exchange.rate.model.ServiceResponse;

public interface RateService {

    ServiceResponse<RateInfo> getLatestRate(String currency);

    ServiceResponse<RateList> getRatesBetweenDates(String start, String end);

    ServiceResponse<RateInfo> getCronLatestRate();

}
