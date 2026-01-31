package com.semihkurucay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.dto.DtoGallerist;
import com.semihkurucay.dto.DtoGalleristIU;
import com.semihkurucay.entity.Gallerist;

public interface IGalleristService {

	public Page<DtoGallerist> findAllPageable(Pageable pageable);
	public DtoGallerist findById(Long id);
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
	public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU);
	public boolean deleteGallerist(Long id);
}
