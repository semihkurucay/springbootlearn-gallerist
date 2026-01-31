package com.semihkurucay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.dto.DtoGalleristCar;
import com.semihkurucay.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public Page<DtoGalleristCar> findAllPageable(Pageable pageable);
	public DtoGalleristCar findById(Long id);
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	public boolean deleteGalleristCar(Long id);
}
