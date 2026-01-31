package com.semihkurucay.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semihkurucay.controller.IRestSoldCarController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoSoldCar;
import com.semihkurucay.dto.DtoSoldCarIU;
import com.semihkurucay.service.ISoldCarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/sold-car")
@RequiredArgsConstructor
public class RestSoldCarControllerImpl extends RestBaseController implements IRestSoldCarController {

	private ISoldCarService soldCarService;

	@PostMapping("/buy-car")
	@Override
	public RootEntity<DtoSoldCar> buyCar(@Valid @RequestBody DtoSoldCarIU soldCar) {
		return ok(soldCarService.buyCar(soldCar));
	}
}
