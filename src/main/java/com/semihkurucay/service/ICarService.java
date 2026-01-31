package com.semihkurucay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.dto.DtoCar;
import com.semihkurucay.dto.DtoCarIU;
import com.semihkurucay.entity.Car;

public interface ICarService {

	public Page<DtoCar> findAllPageable(Pageable pageable);
	public DtoCar findById(Long carId);
	public DtoCar saveCar(DtoCarIU dtoCarIU);
	public DtoCar updateCar(Long carId, DtoCarIU dtoCarIU);
	public boolean deleteCar(Long carId);
}
