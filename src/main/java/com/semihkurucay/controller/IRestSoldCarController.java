package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoSoldCar;
import com.semihkurucay.dto.DtoSoldCarIU;

public interface IRestSoldCarController {

	public RootEntity<DtoSoldCar> buyCar(DtoSoldCarIU soldCar);
}
