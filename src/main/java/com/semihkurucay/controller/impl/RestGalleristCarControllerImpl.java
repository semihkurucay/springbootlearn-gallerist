package com.semihkurucay.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semihkurucay.controller.IRestGalleristCarController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoGalleristCar;
import com.semihkurucay.dto.DtoGalleristCarIU;
import com.semihkurucay.service.IGalleristCarService;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/galleristCar")
@RequiredArgsConstructor
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {
	
	private final IGalleristCarService galleristCarService;
	
	@GetMapping("/list")
	@Override
	public RootEntity<RestPageableEntity<DtoGalleristCar>> findAllPageable(RestPageableRequest pageableRequest) {
		Page<DtoGalleristCar> page = galleristCarService.findAllPageable(toPageable(pageableRequest));
		return ok(toPageableResponse(page, page.getContent()));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoGalleristCar> findById(@PathVariable(value = "id") Long id) {
		return ok(galleristCarService.findById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteGalleristCar(@PathVariable(value = "id") Long id) {
		return ok(galleristCarService.deleteGalleristCar(id));
	}

}
