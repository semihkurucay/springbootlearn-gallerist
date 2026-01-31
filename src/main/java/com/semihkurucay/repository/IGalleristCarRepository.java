package com.semihkurucay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semihkurucay.entity.GalleristCar;

@Repository
public interface IGalleristCarRepository extends JpaRepository<GalleristCar, Long> {

}
