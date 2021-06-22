package com.rukevwe.exchange.rate.provider.coindesk;


import com.rukevwe.exchange.rate.provider.coindesk.models.RateResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author oghenerukevwe
 */
@FeignClient(url = "${coindesk.baseUrl}", configuration = CoindeskFeignConfig.class, name = "coindesk-client")
public interface CoindeskHttpClient {

    @RequestLine("GET /v1/bpi/currentprice/{currency}")
    @Headers({"Content-Type: application/json"})
    RateResponse getLatestRate(@Param("currency") String currency);

    @RequestLine("GET /v1/bpi/historical/close?start={start}&end={end}")
    @Headers({"Content-Type: application/json"})
    RateResponse getRatesBetweenTwoDates(@Param("start") String start, @Param("end") String end);

}
