package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.State;
import com.bootcampASJ.tzappa.Models.Country;

public interface StateRepository extends JpaRepository<State, Integer> {
	List<State> findByCountry(Country country);
}
