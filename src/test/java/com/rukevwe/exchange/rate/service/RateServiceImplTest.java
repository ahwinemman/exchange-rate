package com.rukevwe.exchange.rate.service;

import com.rukevwe.exchange.rate.config.AppConfig;
import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
import com.rukevwe.exchange.rate.model.ServiceResponse;
import com.rukevwe.exchange.rate.provider.RateProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Rate Service Tests")
@ExtendWith(MockitoExtension.class)
public class RateServiceImplTest {

    @InjectMocks
    private RateServiceImpl rateService;
    @Mock
    private RateProviderService rateProviderService;
    @Mock
    private AppConfig appConfig;
    private RateInfo rateInfo;
    private RateList rateList;
    private final String CURRENCY = "USD";

    @BeforeEach
    void setUp() {
        rateInfo = new RateInfo();
        rateInfo.setCurrency(CURRENCY);
        rateInfo.setDescription("description");

        rateList = new RateList();
        rateList.setRateList(new HashMap<>());
        rateList.setCurrency(CURRENCY);
    }

    @Test
    public void getLatestRateShouldReturnSuccessfulServiceResponseWhenRateInfoIsProvided() {

        when(rateProviderService.getProviderLatestRate(any())).thenReturn(rateInfo);

        ServiceResponse serviceResponse = rateService.getLatestRate(CURRENCY);

        assertEquals(RateInfo.class, serviceResponse.getData().getClass());
        assertEquals(null, serviceResponse.getError());
        assertEquals(true, serviceResponse.isSuccess());
    }

    @Test
    public void getRatesBetweenDatesShouldReturnSuccessfulServiceResponseWhenRateListIsProvided() {

        String start = "2021-01-09";
        String end = "2021-01-20";

        when(rateProviderService.getProviderRatesBetweenDates(anyString(), anyString()))
                .thenReturn(rateList);

        ServiceResponse serviceResponse = rateService.getRatesBetweenDates(start, end);

        assertEquals(RateList.class, serviceResponse.getData().getClass());
        assertEquals(null, serviceResponse.getError());
        assertEquals(true, serviceResponse.isSuccess());
    }

    @Test
    public void getCronLatestRateShouldReturnSuccessfulServiceResponseWhenCronJobHasRun() {

        RateServiceImpl.LAST_UPDATED_RATE = new BigDecimal("2234.4444");
        RateServiceImpl.LAST_UPDATED_TIME = "2002-03-04";
        RateServiceImpl.LAST_DESCRIPTION = "description";

        when(appConfig.getDefaultCurrency()).thenReturn(CURRENCY);
        ServiceResponse serviceResponse = rateService.getCronLatestRate();

        assertEquals(RateInfo.class, serviceResponse.getData().getClass());
        RateInfo rateInfo = (RateInfo) serviceResponse.getData();
        assertEquals(null, serviceResponse.getError());
        assertEquals(true, serviceResponse.isSuccess());
        assertEquals("description", rateInfo.getDescription());
        assertEquals("2002-03-04", rateInfo.getLastUpdatedTime());
    }
}
