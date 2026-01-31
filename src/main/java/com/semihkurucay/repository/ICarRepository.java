package com.semihkurucay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semihkurucay.entity.Car;

@Repository
public interface ICarRepository extends JpaRepository<Car, Long> {

}
