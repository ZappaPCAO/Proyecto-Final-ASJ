package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.bootcampASJ.tzappa.utils.ExceptionCustom;
import com.bootcampASJ.tzappa.utils.dataValidation;

import jakarta.transaction.Transactional;

@Service
public class ProviderService {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private SectorRepository sectorRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired 
	private TaxDataRepository taxDataRepository;
	@Autowired
	private ContactDataRepository contactDataRepository;
	
	// Validaciones
	private dataValidation data = new dataValidation();
	
	public List<Provider> getProviders() {
		return this.providerRepository.findAll();	
	}
	
	public List<Provider> getProvidersByActive(){
		return this.providerRepository.findByIsDeletedFalse();
	}
	
	public Provider getProviderById(Integer id) {
		return this.providerRepository.findById(id).get();
	}
	
	public List<Provider> getProviderBySector(Integer id) {
		
		Sector sector = this.sectorRepository.findById(id).get();
		
		return this.providerRepository.findBySector(sector);
	}
	
	public Provider getProviderWithMostOrders(){
		return this.providerRepository.findWithMostOrders();
	}

	@Transactional
	public Provider newProvider(Provider provider) {
		List<ContactData> storedContactsData = this.contactDataRepository.findAll();
		List<TaxData> storedTaxsData = this.taxDataRepository.findAll();
		List<Provider> storedProviders = this.providerRepository.findAll();
	
		if(!data.validateProvider(provider))
			throw new ExceptionCustom("Hay inconsistencias en los datos, verifique y vuelva a mandar.");
		
		for(TaxData taxData : storedTaxsData) {
			if( (provider.getTaxData().getCuit().toLowerCase()).equals(taxData.getCuit().toLowerCase()) )
				throw new ExceptionCustom("Ya hay un registro asociado a ese CUIT.");	
		}
		for(ContactData contactData : storedContactsData){
			if( (provider.getContactData().getEmail().toLowerCase()).equals(contactData.getEmail().toLowerCase()) )
				throw new ExceptionCustom("En datos de contacto ya hay un registro asociado a ese correo.");	
			if((provider.getContactData().getPhone()).equals(contactData.getPhone()))
				throw new ExceptionCustom("En datos de contacto ya hay un registro asociado a ese telefono.");
		}
		System.out.println("llega aca?");
		for(Provider provi : storedProviders) {
			
			if(provider.getLogo() != null && provi.getLogo() != null) {
				if(provider.getLogo().toLowerCase().equals(provi.getLogo()))
					throw new ExceptionCustom("Ya hay un registro asociado a ese logo.");
			}
			if(provider.getWebsite() != null && provi.getWebsite() != null) {
				if(provider.getWebsite().toLowerCase().equals(provi.getWebsite().toLowerCase()))
					throw new ExceptionCustom("Ya hay un registro asociado a ese sitio web.");
			}
			if(provider.getCodProvider().toLowerCase().equals(provi.getCodProvider().toLowerCase()))
				throw new ExceptionCustom("Ya hay un registro asociado a ese codigo de proveedor.");
			if(provider.getBusinessName().toLowerCase().equals(provi.getBusinessName().toLowerCase()))
				throw new ExceptionCustom("Ya hay un registro asociado a esa razon social.");
			if(provider.getPhone().toLowerCase().equals(provi.getPhone().toLowerCase()))
				throw new ExceptionCustom("Ya hay un registro asociado a ese telefono.");
			if(provider.getEmail().toLowerCase().equals(provi.getEmail().toLowerCase()))
				throw new ExceptionCustom("Ya hay un registro asociado a ese correo.");
		}
		System.out.println("llega aca?2");
		this.taxDataRepository.save(provider.getTaxData());
		this.locationRepository.save(provider.getLocation());
		this.contactDataRepository.save(provider.getContactData());
		this.providerRepository.save(provider);
		
	    return provider;	    
	}
	
	@Transactional
	public Provider updateProvider(Provider provider){		
		Location currentLocation = provider.getLocation();
		ContactData currentContactData = provider.getContactData();
		TaxData currentTaxData = provider.getTaxData();
		
		if(!data.validateProvider(provider))
			throw new ExceptionCustom("Hay inconsistencias en los datos, verifique y vuelva a mandar.");
		
		if( provider.equals(this.providerRepository.findById(provider.getId()).get()) )
			throw new ExceptionCustom("El registro que estás intentando editar es idéntico "
					+ "al existente en la base de datos.");
			
		if (currentLocation != null) {	            
			Location storedLocation = this.locationRepository.findById(currentLocation.getId()).get();

	        if (!currentLocation.equals(storedLocation)) {	        	
	        	this.locationRepository.save(currentLocation);
	        }
        }			
		if (currentTaxData != null) {	            
            TaxData storedTaxData = this.taxDataRepository.findById(currentTaxData.getId()).get();
            List <TaxData> storedTaxsData = this.taxDataRepository.findAll();
            
            if (!currentTaxData.equals(storedTaxData)) {
            	for(TaxData taxData : storedTaxsData) {
            		if (currentTaxData.getId() != taxData.getId()) { // Si no es el mismo controlo
	        			if( (currentTaxData.getCuit().toLowerCase()).equals(taxData.getCuit().toLowerCase()) )
	        				throw new ExceptionCustom("Ya hay un registro asociado a ese CUIT.");
            		}
        		}
                this.taxDataRepository.save(currentTaxData);
            }
        }
		if (currentContactData != null) {	            
            ContactData storedContactData = this.contactDataRepository.findById(currentContactData.getId()).get();
            List<ContactData> storedContactsData = this.contactDataRepository.findAll();
            
            if (!currentContactData.equals(storedContactData)) {
            	for(ContactData contactData : storedContactsData){
            		
            		if(currentContactData.getId() != contactData.getId()) {
	        			if( (currentContactData.getEmail().toLowerCase()).equals(contactData.getEmail().toLowerCase()) )        				
	        				throw new ExceptionCustom("En datos de contacto ya hay un registro asociado a ese correo.");	
	        			if( (currentContactData.getPhone()).equals(contactData.getPhone()) )
	        				throw new ExceptionCustom("En datos de contacto ya hay un registro asociado a ese telefono.");
	            	}        			
            	}
            	this.contactDataRepository.save(currentContactData);
            }
		}
		
		List<Provider> storedProviders = this.providerRepository.findAll();
		
		for(Provider provi : storedProviders) {
			if(provider.getId() != provi.getId()) { // Si no es el mismo me fijo.
				
				if(provider.getLogo() != null && provi.getLogo() != null) {
					if(provider.getLogo().toLowerCase().equals(provi.getLogo()))
						throw new ExceptionCustom("Ya hay un registro asociado a ese logo.");
				}
				if(provider.getWebsite() != null && provi.getWebsite() != null) {
					if(provider.getWebsite().toLowerCase().equals(provi.getWebsite().toLowerCase()))
						throw new ExceptionCustom("Ya hay un registro asociado a ese sitio web.");
				}
				
				if(provider.getCodProvider().toLowerCase().equals(provi.getCodProvider().toLowerCase()))
					throw new ExceptionCustom("Ya hay un registro asociado a ese codigo de proveedor.");
				if(provider.getBusinessName().toLowerCase().equals(provi.getBusinessName().toLowerCase()))
					throw new ExceptionCustom("Ya hay un registro asociado a esa razon social.");
				if(provider.getPhone().toLowerCase().equals(provi.getPhone().toLowerCase()))
					throw new ExceptionCustom("Ya hay un registro asociado a ese telefono.");
				if(provider.getEmail().toLowerCase().equals(provi.getEmail().toLowerCase()))
					throw new ExceptionCustom("Ya hay un registro asociado a ese correo.");
			}
		}	
		
		provider.setUpdatedAt(LocalDateTime.now());
		this.providerRepository.save(provider);
		
		return provider;					
	}
	
	@Transactional
	public Provider deleteProvider(Integer id) {
		Provider provider = this.providerRepository.findById(id).get();
		
		if(provider == null)  
			throw new ExceptionCustom("El registro no fue encontrado en la base de datos.");
		if(provider.getIsDeleted()) 
			throw new ExceptionCustom("Este registro ya esta eliminado.");
		
		provider.setUpdatedAt(LocalDateTime.now());
		provider.setIsDeleted(true);
		
		return this.providerRepository.save(provider);
	}
	
	@Transactional
	public Provider rescueProvider(Integer id) {
		Provider provider = this.providerRepository.findById(id).get();
		
		if(provider == null)  
			throw new ExceptionCustom("El registro no fue encontrado en la base de datos.");
		if(!provider.getIsDeleted()) 
			throw new ExceptionCustom("Este registro ya esta salvado.");
		
		provider.setUpdatedAt(LocalDateTime.now());
		provider.setIsDeleted(false);
		
		return this.providerRepository.save(provider);
	}
}
