package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Repositories.ProviderRepository;

import jakarta.transaction.Transactional;

@Service
public class ProviderService {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private SectorService sectorService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private TaxDataService taxDataService;
	@Autowired
	private ContactDataService contactDataService;
	
	public List<Provider> getProviders() {
		return this.providerRepository.findAll();	
	}
	
	public Optional<Provider> getProviderById(Integer id) {
		return this.providerRepository.findById(id);
	}

	@Transactional
	public Optional<Provider> newProvider(Provider provider) {
		
		System.out.println(provider.toString()); 
		
	    try {
	    	this.sectorService.newSector(provider.getSector());
	    	
			this.contactDataService.newContactData(provider.getContactData());
			
			this.taxDataService.newTaxData(provider.getTaxData());
			
			this.locationService.newLocation(provider.getLocation());
			
	        provider.setCreatedAt(LocalDateTime.now());
	        provider.setIsDeleted(false);
	        
	        return Optional.ofNullable(this.providerRepository.save(provider));
	    } catch (DataIntegrityViolationException error ) {
	    	System.out.println("Clave duplicada detectada");
            return Optional.empty();
	    }
	}	
}
