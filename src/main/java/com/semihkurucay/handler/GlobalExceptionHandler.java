package com.semihkurucay.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.semihkurucay.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {BaseException.class})
	public ResponseEntity<ApirError<?>> handlerBaseException(BaseException e, WebRequest request) {
		return ResponseEntity.badRequest().body(createApiError(e.getMessage(), request));
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	private ResponseEntity<ApirError<?>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
		Map<String, List<String>> map = new HashMap<>();
		for(ObjectError objectError : e.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError)objectError).getField();
			
			if(map.containsKey(fieldName)) {
				map.put(fieldName, addValue(map.get(fieldName), objectError.getDefaultMessage()));
			}else {
				map.put(fieldName, addValue(new ArrayList<>(), objectError.getDefaultMessage()));
			}
		}
		
		return ResponseEntity.badRequest().body(createApiError(map, request));
	}
	
	private List<String> addValue(List<String> list, String newValue){
		list.add(newValue);
		return list;
	}
	
	private String getHostName() {
		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	private <T> ApirError<T> createApiError(T errorMessage, WebRequest request){
		ApirError<T> apirError = new ApirError<>();
		apirError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		
		Exception<T> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setErrorMessage(errorMessage);
		exception.setPath(request.getDescription(false).substring(4));
		exception.setHostName(getHostName());
		
		apirError.setException(exception);
		
		return apirError;
	}
	
	
}
