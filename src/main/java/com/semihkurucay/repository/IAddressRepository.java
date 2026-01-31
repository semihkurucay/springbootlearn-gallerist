package com.semihkurucay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semihkurucay.entity.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

}
