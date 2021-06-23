package com.rukevwe.exchange.rate.service;

import com.rukevwe.exchange.rate.config.AppConfig;
import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
import com.rukevwe.exchange.rate.model.ServiceResponse;
import com.rukevwe.exchange.rate.provider.RateProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class RateServiceImpl implements RateService {

    public static String LAST_UPDATED_TIME;
    public static BigDecimal LAST_UPDATED_RATE;
    public static String LAST_DESCRIPTION;
    private RateProviderService rateProviderService;
    private AppConfig appConfig;

    public RateServiceImpl(RateProviderService rateProviderService, AppConfig appConfig) {
        this.rateProviderService = rateProviderService;
        this.appConfig = appConfig;
    }

    @Override
    public ServiceResponse<RateInfo> getLatestRate(String currency) {
        currency = currency == null ? appConfig.getDefaultCurrency() : currency;
        RateInfo rateInfo = rateProviderService.getProviderLatestRate(currency);
        ServiceResponse<RateInfo> serviceResponse = new ServiceResponse<>();
        serviceResponse.setSuccess(true);
        serviceResponse.setMessage("Successfully retrieved exchange rate");
        serviceResponse.setData(rateInfo);
        return serviceResponse;
    }

    @Override
    public ServiceResponse<RateList> getRatesBetweenDates(String start, String end) {
        RateList rateList = rateProviderService.getProviderRatesBetweenDates(start, end);
        ServiceResponse<RateList> serviceResponse = new ServiceResponse<>();
        serviceResponse.setSuccess(true);
        serviceResponse.setMessage("Successfully retrieved exchange rates between dates");
        serviceResponse.setData(rateList);
        return serviceResponse;
    }

    @Override
    public ServiceResponse<RateInfo> getCronLatestRate() {
        ServiceResponse<RateInfo> serviceResponse = new ServiceResponse<>();
        RateInfo rateInfo = new RateInfo();
        serviceResponse.setSuccess(true);
        serviceResponse.setMessage("Successfully retrieved cron latest exchange rate");
        rateInfo.setDescription(LAST_DESCRIPTION);
        rateInfo.setRate(LAST_UPDATED_RATE);
        rateInfo.setCurrency(appConfig.getDefaultCurrency());
        rateInfo.setLastUpdatedTime(LAST_UPDATED_TIME);
        serviceResponse.setData(rateInfo);
        return serviceResponse;
    }


    @Scheduled(cron = "${schedule.updateExchangeRate}")
    public void updateExchangeRate() {
        try {
            RateInfo rateInfo = rateProviderService.getProviderLatestRate(appConfig.getDefaultCurrency());
            LAST_UPDATED_TIME = rateInfo.getLastUpdatedTime();
            LAST_DESCRIPTION = rateInfo.getDescription();
            LAST_UPDATED_RATE = rateInfo.getRate();
        } catch (Exception ex) {
            log.error("Error while attempting to retrieve latest exchange rate ", ex);
        }

    }
}
