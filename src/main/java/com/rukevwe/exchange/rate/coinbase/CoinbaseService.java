package com.rukevwe.exchange.rate.coinbase;

import com.rukevwe.exchange.rate.model.ServiceResponse;
import com.rukevwe.exchange.rate.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class CoinbaseService implements RateService {

    private CoinbaseHttpClient httpClient;
    private static String LAST_UPDATED_TIME;
    private static String LAST_UPDATED_USD_RATE;
    private static final String USD_CURRENCY = "USD";


    @Autowired
    public CoinbaseService(CoinbaseHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public ServiceResponse getLatestRate(String currency) {
        System.out.println(httpClient.getLatestRate("USD"));
        System.out.println(httpClient.getLatestRate("USD").getDisclaimer());
        System.out.println(httpClient.getLatestRate("USD").getTime().getUpdated());

        return null;
    }

    @Override
    public ServiceResponse getRatesBetweenDates(String start, String end) {
        return null;
    }


    @Scheduled(cron = "${schedule.updateExchangeRate}")
    public void updateExchangeRate() {

        ServiceResponse serviceResponse = getLatestRate(USD_CURRENCY);


    }

}
