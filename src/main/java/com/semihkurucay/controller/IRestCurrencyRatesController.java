package com.semihkurucay.controller;

import com.semihkurucay.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

	public RootEntity<CurrencyRatesResponse> getCurrencyRatesResponse(String startDate, String endDate);
}
