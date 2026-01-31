package com.semihkurucay.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semihkurucay.dto.DtoGalleristCar;
import com.semihkurucay.dto.DtoGalleristCarIU;
import com.semihkurucay.entity.Car;
import com.semihkurucay.entity.Gallerist;
import com.semihkurucay.entity.GalleristCar;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.IGalleristCarMapper;
import com.semihkurucay.repository.ICarRepository;
import com.semihkurucay.repository.IGalleristCarRepository;
import com.semihkurucay.repository.IGalleristRepository;
import com.semihkurucay.service.IGalleristCarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleristCarServiceImpl implements IGalleristCarService {
	
	private final IGalleristCarRepository galleristCarRepository;
	
	private final IGalleristRepository galleristRepository;
	
	private final ICarRepository carRepository;
	
	private final IGalleristCarMapper galleristCarMapper;
	
	@Override
	public Page<DtoGalleristCar> findAllPageable(Pageable pageable) {
		return galleristCarRepository.findAll(pageable).map(galleristCarMapper::toDto);
	}
	
	private GalleristCar getGalleristCarOrThrow(Long id) {
		return galleristCarRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST)));
	}
	
	@Override
	public DtoGalleristCar findById(Long id) {
		return galleristCarMapper.toDto(getGalleristCarOrThrow(id));
	}
	
	@Transactional
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		Gallerist gallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId())
				.orElseThrow(() -> new BaseException(new ErrorMessage(dtoGalleristCarIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST)));
		
		Car car = carRepository.findById(dtoGalleristCarIU.getCarId())
				.orElseThrow(() -> new BaseException(new ErrorMessage(dtoGalleristCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST)));
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setGallerist(gallerist);
		galleristCar.setCar(car);
		
		return galleristCarMapper.toDto(galleristCarRepository.save(galleristCar));
	}

	@Override
	public boolean deleteGalleristCar(Long id) {
		galleristCarRepository.delete(getGalleristCarOrThrow(id));
		return true;
	}

}
