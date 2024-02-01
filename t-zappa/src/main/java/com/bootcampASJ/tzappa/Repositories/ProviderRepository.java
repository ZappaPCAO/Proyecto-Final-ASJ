package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.Sector;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	List<Provider> findBySector(Sector sector);
}
