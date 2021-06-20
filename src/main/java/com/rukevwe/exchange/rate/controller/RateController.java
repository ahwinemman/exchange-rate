package com.rukevwe.exchange.rate.controller;

import com.rukevwe.exchange.rate.model.ServiceResponse;
import com.rukevwe.exchange.rate.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(path = "/latest")
    public ServiceResponse getLatestRate(@RequestParam(value = "currency", defaultValue = "USD")
                                                     String currency) {
        rateService.getLatestRate(currency);
        return null;
    }

    @GetMapping
    public ServiceResponse getRatesBetweenDates(@RequestParam("start") String start,
                                                @RequestParam("end") String end) {
        rateService.getRatesBetweenDates(start, end);
        return null;
    }


}
