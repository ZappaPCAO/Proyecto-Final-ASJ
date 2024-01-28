package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Repositories.ProviderRepository;

import jakarta.validation.Valid;

@Service
public class ProviderService {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	public List<Provider> getProviders() {
		return this.providerRepository.findAll();	
	}
	
	public Optional<Provider> getProviderById(Integer id) {
		return this.providerRepository.findById(id);
	}

	public Optional<Provider> newProvider(Provider provider) {
	    try {
	        provider.setCreated_at(LocalDateTime.now());
	        provider.setIs_deleted(false);
	        
	        return Optional.ofNullable(this.providerRepository.save(provider));
	    } catch (DataIntegrityViolationException error) {	   
	    	
	        return Optional.empty();
	    }
	}	
}
