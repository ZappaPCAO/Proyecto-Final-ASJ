package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.Sector;
import com.bootcampASJ.tzappa.Models.Location;
import com.bootcampASJ.tzappa.Models.TaxData;
import com.bootcampASJ.tzappa.Models.ContactData;

import com.bootcampASJ.tzappa.Repositories.ContactDataRepository;
import com.bootcampASJ.tzappa.Repositories.LocationRepository;
import com.bootcampASJ.tzappa.Repositories.ProviderRepository;
import com.bootcampASJ.tzappa.Repositories.SectorRepository;
import com.bootcampASJ.tzappa.Repositories.TaxDataRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

@Service
public class ProviderService {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private TaxDataService taxDataService;
	@Autowired
	private ContactDataService contactDataService;
	
	// Prueba con solo los jpa.
	
	@Autowired
	private SectorRepository sectorRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired 
	private TaxDataRepository taxDataRepository;
	@Autowired
	private ContactDataRepository contactDataRepository;
	
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
	    	
			this.contactDataService.newContactData(provider.getContactData());
			
			this.taxDataService.newTaxData(provider.getTaxData());
			
			this.locationService.newLocation(provider.getLocation());
	        
	        return Optional.ofNullable(this.providerRepository.save(provider));
	    } catch (DataIntegrityViolationException error) {
	    	System.out.println("Clave duplicada detectada");
            return Optional.empty();
	    }
	}
	
	@Transactional
	public Optional<Provider> updateProvider(Provider provider){
		try {
			System.out.println("Llegue!!");		
			Location currentLocation = provider.getLocation();
			ContactData currentContactData = provider.getContactData();
			TaxData currentTaxData = provider.getTaxData();
			
			if (currentLocation != null) {	            
	            Location storedLocation = this.locationRepository.findById(currentLocation.getId()).get();

	            if (!currentLocation.equals(storedLocation)) {
	                this.locationRepository.save(currentLocation);
	            }
	        }			
			if (currentTaxData != null) {	            
	            TaxData storedTaxData = this.taxDataRepository.findById(currentTaxData.getId()).get();

	            if (!currentTaxData.equals(storedTaxData)) {
	                this.taxDataRepository.save(currentTaxData);
	            }
	        }
			if (currentContactData != null) {	            
	            ContactData storedContactData = this.contactDataRepository.findById(currentContactData.getId()).get();

	            if (!currentContactData.equals(storedContactData)) {
	                this.contactDataRepository.save(currentContactData);
	            }
	        }
			
			provider.setUpdatedAt(LocalDateTime.now());
			
			return Optional.ofNullable(this.providerRepository.save(provider));
			
		}catch (RuntimeException error) {
		    if (error instanceof DataIntegrityViolationException) {
		        if (error.getCause() instanceof ConstraintViolationException) {
		            System.out.println("Clave duplicada detectada");
		        } else {
		            // Manejar otras violaciones de integridad de datos si es necesario
		        }
		    } else if (error instanceof NoSuchElementException) {		       
		        System.out.println("La entidad no fue encontrada en la base de datos");
		    }
		    return Optional.empty();
		}						
	}
	
	@Transactional
	public Optional<Provider> deleteProvider(Integer id) {
		try {
			Provider provider = this.providerRepository.findById(id).get();
			
			if(provider != null) {
				provider.setIsDeleted(true);
				
				return Optional.ofNullable(this.providerRepository.save(provider));
			}
			
			return Optional.empty();
			
		}catch (RuntimeException error) {
			if (error instanceof NoSuchElementException) {		       
		        System.out.println("La entidad no fue encontrada en la base de datos");
		    }
		    return Optional.empty();
		}
	}
	
	@Transactional
	public Optional<Provider> rescueProvider(Integer id) {
		try {
			Provider provider = this.providerRepository.findById(id).get();
			
			if(provider != null) {
				provider.setIsDeleted(false);
				
				return Optional.ofNullable(this.providerRepository.save(provider));
			}
			
			return Optional.empty();
			
		}catch (RuntimeException error) {
			if (error instanceof NoSuchElementException) {		       
		        System.out.println("La entidad no fue encontrada en la base de datos");
		    }
		    return Optional.empty();
		}
	}
	
	public Optional<List<Provider>> getProviderBySector(Integer id) {
		
		Sector sector = this.sectorRepository.findById(id).get();
		
		return Optional.ofNullable(this.providerRepository.findBySector(sector));
	}
	
}
