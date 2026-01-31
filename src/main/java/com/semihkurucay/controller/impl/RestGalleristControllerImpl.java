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

import com.semihkurucay.controller.IRestGalleristController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoGallerist;
import com.semihkurucay.dto.DtoGalleristIU;
import com.semihkurucay.service.IGalleristService;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/gallerist")
@RequiredArgsConstructor
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {
	
	private final IGalleristService galleristService;
	
	@GetMapping("/list")
	@Override
	public RootEntity<RestPageableEntity<DtoGallerist>> findAllPageable(RestPageableRequest request) {
		Page<DtoGallerist> page = galleristService.findAllPageable(toPageable(request));
		return ok(toPageableResponse(page, page.getContent()));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoGallerist> getGallerist(@PathVariable(value = "id") Long id) {
		return ok(galleristService.findById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> save(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGallerist> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.updateGallerist(id, dtoGalleristIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
		return ok(galleristService.deleteGallerist(id));
	}

}
