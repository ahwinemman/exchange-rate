package com.rukevwe.exchange.rate.provider.coindesk;


import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
import com.rukevwe.exchange.rate.provider.coindesk.models.RateResponse;
import com.rukevwe.exchange.rate.provider.coindesk.models.Time;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Coindesk Service Tests")
@ExtendWith(MockitoExtension.class)
public class CoindeskServiceTest {

    @InjectMocks
    private CoindeskService coindeskService;
    @Mock
    private CoindeskHttpClient httpClient;
    private String CURRENCY = "USD";


    @Test
    public void getProviderLatestRateShouldReturnExpectedRateInfo() {
        RateResponse<Map<String, String>> rateResponse = new RateResponse<>();

        Time time = new Time();
        time.setUpdatedISO("2012-02-01");
        rateResponse.setTime(time);
        rateResponse.setDisclaimer("disclaimer");

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("rate", "349293");
        dataMap.put("description", "description");

        Map<String, Map<String, String>> bpiMap = new HashMap<>();
        bpiMap.put(CURRENCY, dataMap);

        rateResponse.setBpi(bpiMap);

        when(httpClient.getLatestRate(any())).thenReturn(rateResponse);

        RateInfo rateInfo = coindeskService.getProviderLatestRate(CURRENCY);

        assertEquals("description", rateInfo.getDescription());
        assertEquals(new BigDecimal("349293"), rateInfo.getRate());

    }

    @Test
    public void getProviderRatesBetweenDatesShouldReturnExpectedRateList() {
        RateResponse<BigDecimal> rateResponse = new RateResponse<>();

        Time time = new Time();
        time.setUpdatedISO("2012-02-01");
        rateResponse.setTime(time);
        rateResponse.setDisclaimer("disclaimer");

        Map<String, BigDecimal> bpiMap = new HashMap<>();
        bpiMap.put("2012-02-01", new BigDecimal("34940.3901"));

        rateResponse.setBpi(bpiMap);

        when(httpClient.getRatesBetweenTwoDates(anyString(), anyString())).thenReturn(rateResponse);

        RateList rateList = coindeskService.getProviderRatesBetweenDates("start", "end");

        assertEquals(CURRENCY, rateList.getCurrency());
        assertEquals("2012-02-01", rateList.getLastUpdatedTime());
        assertEquals(new BigDecimal("34940.3901"), rateList.getRateList().get("2012-02-01"));

    }
}
