package com.semihkurucay.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.semihkurucay.dto.CurrencyRatesResponse;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.service.ICurrencyRateService;

@Service
public class CurrencyRateServiceImpl implements ICurrencyRateService {

	// İtek atılacak url :
	// https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=30-01-2026&endDate=30-01-2026&type=json

	@Override
	public CurrencyRatesResponse getCurrencyRatesResponse(String startDate, String endDate) {
		final String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
		String series = "TP.DK.USD.A";
		String type = "json";
		final String key = "vUXU1fszjY";
		
		String endpoint = rootURL + "/series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("key", key);
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
			
			});
			
			if(response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.CURRENCY_RATES_IS_OCCURED));
		}
		
		return null;
	}

}
