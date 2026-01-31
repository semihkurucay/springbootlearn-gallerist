package com.semihkurucay.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApirError <T> {
	
	private Integer status;
	private Exception<T> exception;
}
