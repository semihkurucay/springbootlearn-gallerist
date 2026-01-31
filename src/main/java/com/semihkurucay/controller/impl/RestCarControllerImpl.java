package com.semihkurucay.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semihkurucay.controller.IRestCarController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoCar;
import com.semihkurucay.dto.DtoCarIU;
import com.semihkurucay.service.ICarService;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/car")
@RequiredArgsConstructor
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {
	
	private final ICarService carService;
	
	@GetMapping("/list")
	@Override
	public RootEntity<RestPageableEntity<DtoCar>> findAllPageable(RestPageableRequest pePageableRequest) {
		Page<DtoCar> page = carService.findAllPageable(toPageable(pePageableRequest));
		return ok(toPageableResponse(page, page.getContent()));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoCar> findById(@PathVariable(value = "id") Long carId) {
		return ok(carService.findById(carId));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@PathVariable(value = "id") Long carId, @Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.updateCar(carId, dtoCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteCar(@PathVariable(value = "id") Long carId) {
		return ok(carService.deleteCar(carId));
	}

}
