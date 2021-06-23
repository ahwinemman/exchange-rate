package com.rukevwe.exchange.rate.provider.coindesk;

import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
import com.rukevwe.exchange.rate.provider.RateProviderService;
import com.rukevwe.exchange.rate.provider.coindesk.models.RateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author oghenerukevwe
 */
@Service
@Slf4j
public class CoindeskService implements RateProviderService {

    private CoindeskHttpClient httpClient;

    @Autowired
    public CoindeskService(CoindeskHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public RateInfo getProviderLatestRate(String currency) {
        RateResponse<Map<String, String>> rateResponse = httpClient.getLatestRate(currency);
        RateInfo rateInfo = new RateInfo();
        rateInfo.setCurrency(currency);
        rateInfo.setDescription(rateResponse.getBpi().get(currency).get("description"));
        rateInfo.setRate(new BigDecimal(rateResponse.getBpi().get(currency)
                .get("rate").replace(",", "")));
        rateInfo.setLastUpdatedTime(rateResponse.getTime().getUpdatedISO());
        return rateInfo;
    }

    @Override
    public RateList getProviderRatesBetweenDates(String start, String end) {
        RateResponse<BigDecimal> listResponse = httpClient.getRatesBetweenTwoDates(start, end);
        RateList rateList = new RateList();
        rateList.setRateList((listResponse.getBpi()));
        rateList.setCurrency("USD");
        rateList.setLastUpdatedTime(listResponse.getTime().getUpdatedISO());
        return rateList;
    }

}
