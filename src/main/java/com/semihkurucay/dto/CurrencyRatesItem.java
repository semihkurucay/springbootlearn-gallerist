package com.semihkurucay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesItem {

	@JsonProperty("Tarih") //Gelen json bilgisindeki title ile bizim değişkenimizi mapledik. İsimler aynı olsaydı gerek yoktu
	private String date;
	
	@JsonProperty("TP_DK_USD_A")
	private String usd;
}
