package com.rukevwe.exchange.rate.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JavascriptMessageConverter extends AbstractJackson2HttpMessageConverter {

    public JavascriptMessageConverter() {
        super(Jackson2ObjectMapperBuilder.json().build(), new MediaType("application", "javascript"));
    }
}
