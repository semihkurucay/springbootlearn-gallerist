package com.semihkurucay.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGallerist extends DtoBaseEntity {

	private String firstName;
	private String lastName;
	private DtoAddress address;
}
