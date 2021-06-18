package com.rukevwe.exchange.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class ExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);
	}

}
