package com.rukevwe.exchange.rate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("app")
@Component
@Data
public class AppConfig {
    private String defaultCurrency;
}
