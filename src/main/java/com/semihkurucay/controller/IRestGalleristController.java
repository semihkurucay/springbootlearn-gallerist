package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoGallerist;
import com.semihkurucay.dto.DtoGalleristIU;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public interface IRestGalleristController {

	public RootEntity<RestPageableEntity<DtoGallerist>> findAllPageable(RestPageableRequest request);
	public RootEntity<DtoGallerist> getGallerist(Long id);
	public RootEntity<DtoGallerist> save(DtoGalleristIU dtoGalleristIU);
	public RootEntity<DtoGallerist> update(Long id, DtoGalleristIU dtoGalleristIU);
	public RootEntity<Boolean> delete(Long id);
}
