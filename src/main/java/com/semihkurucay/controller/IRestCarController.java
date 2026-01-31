package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoCar;
import com.semihkurucay.dto.DtoCarIU;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public interface IRestCarController {

	public RootEntity<RestPageableEntity<DtoCar>> findAllPageable(RestPageableRequest pePageableRequest);
	public RootEntity<DtoCar> findById(Long carId);
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	public RootEntity<DtoCar> updateCar(Long carId, DtoCarIU dtoCarIU);
	public RootEntity<Boolean> deleteCar(Long carId);
}
