package com.rukevwe.exchange.rate.provider;

import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;

public interface RateProviderService {

    RateInfo getProviderLatestRate(String currency);

    RateList getProviderRatesBetweenDates(String currency, String start, String end);

}
