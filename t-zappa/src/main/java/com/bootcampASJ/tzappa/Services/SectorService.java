package com.bootcampASJ.tzappa.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Sector;
import com.bootcampASJ.tzappa.Repositories.SectorRepository;

@Service
public class SectorService {
	
	@Autowired
	private SectorRepository sectorRepository;
	
	public List<Sector> getSectors() {
		return this.sectorRepository.findAll();	
	}

	public Optional<Sector> newSector(Sector sector) {
	    try {
	        return Optional.ofNullable(this.sectorRepository.save(sector));
	    } catch (DataIntegrityViolationException error) {	        
	    	throw new DataIntegrityViolationException("Clave duplicada en Sectors");
	    }
	}
	
	public Optional<Sector> updateSector(Sector sector){
		try {
			return ( (this.sectorRepository.findById(sector.getId()) != null) ?
					Optional.ofNullable(this.sectorRepository.save(sector)) : null );			
		}catch(DataIntegrityViolationException error) {
			throw new DataIntegrityViolationException("[" + error.toString() + "] Clave duplicada en Sectors");
		}		
	}
	
	public Boolean deleleteSector(Integer id) {
		Sector sector = this.sectorRepository.findById(id).get();
		
		if(sector != null) {
			sector.setIsDeleted(true);
			
			this.sectorRepository.save(sector);
		}
		
		return (sector != null);			
	}
	
}
