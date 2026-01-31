package com.semihkurucay.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoBaseEntity {
	private Long id;
	private Date createTime;
}
