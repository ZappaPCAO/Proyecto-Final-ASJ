package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.City;
import com.bootcampASJ.tzappa.Models.State;

public interface CityRepository extends JpaRepository<City, Integer> {

	 List<City> findByState(State state);
}
