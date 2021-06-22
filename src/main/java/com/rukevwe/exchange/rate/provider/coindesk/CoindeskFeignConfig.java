package com.rukevwe.exchange.rate.provider.coindesk;

import com.rukevwe.exchange.rate.config.JavascriptMessageConverter;
import com.rukevwe.exchange.rate.exception.InvalidRequestException;
import com.rukevwe.exchange.rate.exception.ProviderException;
import feign.Contract;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author oghenerukevwe
 */
@Slf4j
public class CoindeskFeignConfig {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    @Bean
    Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new JavascriptMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            HttpStatus responseStatus = HttpStatus.valueOf(response.status());
            log.error("Error reaching coindesk service. Method: {}, Status Code: {}", methodKey, responseStatus.value());
            if (responseStatus.is4xxClientError()) {
                return new InvalidRequestException();
            } else {
                return new ProviderException();
            }
        };
    }

}
