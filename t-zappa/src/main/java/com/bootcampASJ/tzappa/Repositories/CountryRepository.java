package com.bootcampASJ.tzappa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {}
