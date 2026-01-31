package com.semihkurucay.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semihkurucay.dto.DtoGallerist;
import com.semihkurucay.dto.DtoGalleristIU;
import com.semihkurucay.entity.Address;
import com.semihkurucay.entity.Gallerist;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.IGalleristMapper;
import com.semihkurucay.repository.IAddressRepository;
import com.semihkurucay.repository.IGalleristRepository;
import com.semihkurucay.service.IGalleristService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleristServiceImpl implements IGalleristService {
	
	private final IGalleristRepository galleristRepository;
	
	private final IAddressRepository addressRepository;
	
	private final IGalleristMapper galleristMapper;
	
	@Override
	public Page<DtoGallerist> findAllPageable(Pageable pageable) {
		return galleristRepository.findAll(pageable).map(galleristMapper::toDto);
	}

	private Gallerist getGalleristOrThrow(Long id) {
		return galleristRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST)));
	}
	
	@Override
	public DtoGallerist findById(Long id) {
		return galleristMapper.toDto(getGalleristOrThrow(id));
	}

	@Transactional
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		Address address = addressRepository.findById(dtoGalleristIU.getAddressId())
				.orElseThrow(() -> new BaseException(new ErrorMessage(dtoGalleristIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST)));
		
		Gallerist gallerist = galleristMapper.toGallerist(dtoGalleristIU);
		gallerist.setCreateTime(new Date());
		gallerist.setAddress(address);
		
		return galleristMapper.toDto(galleristRepository.save(gallerist));
	}

	@Override
	public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {
		Gallerist gallerist = galleristMapper.updateGallerist(dtoGalleristIU, getGalleristOrThrow(id));
		
		return galleristMapper.toDto(galleristRepository.save(gallerist));
	}

	@Override
	public boolean deleteGallerist(Long id) {
		galleristRepository.delete(getGalleristOrThrow(id));
		return true;
	}

}
