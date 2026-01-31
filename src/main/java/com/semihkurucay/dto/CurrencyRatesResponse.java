package com.semihkurucay.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesResponse {

	private Integer totalCount;
	
	private List<CurrencyRatesItem> items;
}


/*
GELEN RESPONSE

{
"totalCount": 1,
"items": [
    {
        "Tarih": "30-01-2026",
        "TP_DK_USD_A": "43.3443",
        "UNIXTIME": {
            "$numberLong": "1769720400"
        }
    }
]
}
*/