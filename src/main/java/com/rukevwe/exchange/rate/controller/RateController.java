package com.rukevwe.exchange.rate.controller;

import com.rukevwe.exchange.rate.model.RateInfo;
import com.rukevwe.exchange.rate.model.RateList;
import com.rukevwe.exchange.rate.model.ServiceResponse;
import com.rukevwe.exchange.rate.service.RateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rates", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Exchange Rate APIs", tags = "exchange-rate")
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @ApiOperation(value = "Get latest exchange rate, default currency is USD",
            produces = MediaType.APPLICATION_JSON_VALUE,
            notes = "Endpoint expects currency in ISO format i.e. USD for United States Dollar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved exchange rate", response = ServiceResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ServiceResponse.class),
            @ApiResponse(code = 500, message = "Server error", response = ServiceResponse.class)
    })
    @GetMapping(path = "/latest")
    public ServiceResponse<RateInfo> getLatestRate(@ApiParam(value = "currency", defaultValue = "USD")
                                                   @RequestParam(value = "currency", required = false) String currency) {
        return rateService.getLatestRate(currency);
    }

    @ApiOperation(value = "Get latest exchange rate from cron job running at a configurable schedule, default currency is USD",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved cron latest exchange rate", response = ServiceResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ServiceResponse.class),
            @ApiResponse(code = 500, message = "Server error", response = ServiceResponse.class)
    })
    @GetMapping(path = "/cron/latest")
    public ServiceResponse<RateInfo> getLatestCronRate() {
        return rateService.getCronLatestRate();
    }

    @ApiOperation(value = "Get exchange rates between a start and end date, default currency is USD",
            produces = MediaType.APPLICATION_JSON_VALUE,
            notes = "Endpoint expects a start and end date in the format yyyy-MM-dd i.e. 2020-09-01")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved exchange rates between dates", response = ServiceResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ServiceResponse.class),
            @ApiResponse(code = 500, message = "Server error", response = ServiceResponse.class)
    })
    @GetMapping(path = "/range")
    public ServiceResponse<RateList> getRatesBetweenDates(@RequestParam("start") String start,
                                                          @RequestParam("end") String end) {
        return rateService.getRatesBetweenDates(start, end);
    }


}
