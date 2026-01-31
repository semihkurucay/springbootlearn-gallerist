package com.semihkurucay.service;

import com.semihkurucay.dto.DtoSoldCar;
import com.semihkurucay.dto.DtoSoldCarIU;

public interface ISoldCarService {

	public DtoSoldCar buyCar(DtoSoldCarIU dtoSeladCarIU);
}
