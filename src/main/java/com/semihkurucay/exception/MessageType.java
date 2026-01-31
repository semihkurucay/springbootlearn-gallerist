package com.semihkurucay.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST(1001, "Kayıt bulunamadı!"),
	TOKEN_IS_EXPIRED(1002, "Token süresi dolmuştur!"),
	USERNAME_NOT_FOUND(1003, "Kullanıcı adı bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID(1004, "Kullanıcı adı veya şifre hatalı!"),
	REFRESH_TOKEN_NOT_FOUND(1005, "Refresh token bulunamadı!"),
	REFRESH_TOKEN_IS_EXPIRED(1006, "Token süresi dolmuştur!"),
	RECURRENT_USERNAME(1007, "Kayıtlı kullanıcı adı!"),
	CURRENCY_RATES_IS_OCCURED(1008, "Döviz kuru alınamadı"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH(1009, "Müşterinin bakiyesi yeterli değil"),
	CAR_STATUS_IS_ALREAD_SALED(1010, "Araba satılmış, tekrar satılamaz"),
	GENERAL_EXCEPTION(9998, "Genel bir hata oluştur"),
	UNKNOW_EXCEPTİON(9999, "Bilinmeyen bir hata oluştu!");
	
	private Integer code;
	private String message;
	
	MessageType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
