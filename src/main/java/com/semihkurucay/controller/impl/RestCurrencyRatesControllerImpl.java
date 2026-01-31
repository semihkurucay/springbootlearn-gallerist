package com.semihkurucay.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semihkurucay.controller.IRestCurrencyRatesController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.CurrencyRatesResponse;
import com.semihkurucay.service.ICurrencyRateService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/rest/api")
@RequiredArgsConstructor
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {

	private final ICurrencyRateService currencyRateService;
	
	@GetMapping("/currency-rates")
	@Override
	public RootEntity<CurrencyRatesResponse> getCurrencyRatesResponse(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		return ok(currencyRateService.getCurrencyRatesResponse(startDate, endDate));
	}

}
