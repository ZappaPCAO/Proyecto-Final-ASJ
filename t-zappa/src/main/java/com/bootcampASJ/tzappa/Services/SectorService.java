package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Sector;
import com.bootcampASJ.tzappa.Repositories.SectorRepository;

import jakarta.transaction.Transactional;

@Service
public class SectorService {
	
	@Autowired
	private SectorRepository sectorRepository;

	@Transactional
	public Optional<Sector> newSector(Sector sector) {
	    try {
	        sector.setCreatedAt(LocalDateTime.now());
	        sector.setIsDeleted(false);
	        
	        return Optional.ofNullable(this.sectorRepository.save(sector));
	    } catch (DataIntegrityViolationException error) {	   
	    	
	        return Optional.empty();
	    }
	}	
	
}
