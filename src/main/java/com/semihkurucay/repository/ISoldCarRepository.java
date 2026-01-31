package com.semihkurucay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semihkurucay.entity.SoldCar;

@Repository
public interface ISoldCarRepository extends JpaRepository<SoldCar, Long> {

}
