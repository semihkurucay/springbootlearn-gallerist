package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoGalleristCar;
import com.semihkurucay.dto.DtoGalleristCarIU;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public interface IRestGalleristCarController {

	public RootEntity<RestPageableEntity<DtoGalleristCar>> findAllPageable(RestPageableRequest pageableRequest);
	public RootEntity<DtoGalleristCar> findById(Long id);
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	public RootEntity<Boolean> deleteGalleristCar(Long id);
}
