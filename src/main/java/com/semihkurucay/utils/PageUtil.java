package com.semihkurucay.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PageUtil {

	private boolean isEmptyOrNull(String value) {
		return value == null || value.isEmpty() || value.length() == 0;
	}
	
	public Pageable toPageable(RestPageableRequest request) {
		if(!isEmptyOrNull(request.getColumnName())) {
			Sort sort = request.isAsc() ? Sort.by(Direction.ASC, request.getColumnName()) : Sort.by(Direction.DESC, request.getColumnName());
			
			return PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
		}
		
		return PageRequest.of(request.getPageNumber(), request.getPageSize());
	}
	
	public <T> RestPageableEntity<T> pageableResponse(Page<?> page, List<T> contanet){
		RestPageableEntity<T> restPageableEntity = new RestPageableEntity<>();
		restPageableEntity.setContent(contanet);
		restPageableEntity.setPageNumber(page.getPageable().getPageNumber());
		restPageableEntity.setPageSize(page.getPageable().getPageSize());
		restPageableEntity.setTotalElemants(page.getTotalElements());
		
		return restPageableEntity;
	}
}
