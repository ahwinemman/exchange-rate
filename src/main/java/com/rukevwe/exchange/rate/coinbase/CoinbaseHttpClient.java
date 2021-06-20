package com.rukevwe.exchange.rate.coinbase;


import com.rukevwe.exchange.rate.coinbase.models.CBLatestRateResponse;
import com.rukevwe.exchange.rate.coinbase.models.CBRateHistoryResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author oghenerukevwe
 */
@FeignClient(url = "${coindesk.baseUrl}", configuration = CoinbaseFeignConfig.class, name = "coindesk-client")
public interface CoinbaseHttpClient {

    @RequestLine("GET /v1/bpi/currentprice/{currency}")
    @Headers({"Content-Type: application/json"})
    CBLatestRateResponse getLatestRate(@Param("currency") String currency);

    @RequestLine("POST /v1/bpi/historical/close?start={start}&end={end}")
    @Headers({"Content-Type: application/json"})
    CBRateHistoryResponse getRatesBetweenTwoDates(@Param("start") String start, @Param("end") String end);

}
