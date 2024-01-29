package com.bootcampASJ.tzappa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.TaxData;

public interface TaxDataRepository extends JpaRepository<TaxData, Integer> {}
