package com.semihkurucay.service;

import com.semihkurucay.dto.CurrencyRatesResponse;

public interface ICurrencyRateService {

	public CurrencyRatesResponse getCurrencyRatesResponse(String startDate, String endDate);
}
