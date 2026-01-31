package com.semihkurucay.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.utils.PageUtil;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public class RestBaseController {

	public Pageable toPageable(RestPageableRequest pageableRequest) {
		return PageUtil.toPageable(pageableRequest);
	}
	
	public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> collection){
		return PageUtil.pageableResponse(page, collection);
	}
	
	public <T>  RootEntity<T> ok(T payload) {
		return RootEntity.ok(payload);
	}
	
	public <T> RootEntity<T> error(String errorMessage){
		return RootEntity.error(errorMessage);
	}
}
