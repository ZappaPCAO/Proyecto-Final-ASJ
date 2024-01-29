package com.bootcampASJ.tzappa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {}