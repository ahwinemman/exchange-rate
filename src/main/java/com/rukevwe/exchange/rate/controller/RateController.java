package com.rukevwe.exchange.rate.controller;

import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
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

    @GetMapping
    public ServiceResponse<RateInfo> getLatestRate(@RequestParam(value = "currency") String currency) {
        return rateService.getLatestRate(currency);
    }

    @GetMapping(path = "/cron")
    public ServiceResponse<RateInfo> getLatestCronRate() {
        return rateService.getCronLatestRate();
    }

    @GetMapping(path = "/range")
    public ServiceResponse<RateList> getRatesBetweenDates(@RequestParam(value = "currency") String currency,
                                                          @RequestParam("start") String start,
                                                          @RequestParam("end") String end) {
        return rateService.getRatesBetweenDates(currency, start, end);
    }


}
