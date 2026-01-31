package com.semihkurucay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semihkurucay.entity.Gallerist;

@Repository
public interface IGalleristRepository extends JpaRepository<Gallerist, Long> {

}
