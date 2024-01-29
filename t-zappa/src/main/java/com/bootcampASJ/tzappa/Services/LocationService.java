package com.bootcampASJ.tzappa.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Location;
import com.bootcampASJ.tzappa.Repositories.LocationRepository;

import jakarta.transaction.Transactional;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Transactional
	public Optional<Location> newLocation(Location location) {
	    try {	        
	        return Optional.ofNullable(this.locationRepository.save(location));
	    } catch (DataIntegrityViolationException error) {	   
	    	
	        return Optional.empty();
	    }
	}
}
