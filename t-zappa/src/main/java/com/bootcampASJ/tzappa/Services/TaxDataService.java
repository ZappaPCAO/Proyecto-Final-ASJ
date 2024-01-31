package com.bootcampASJ.tzappa.Services;

import java.util.Optional;

import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.TaxData;
import com.bootcampASJ.tzappa.Repositories.TaxDataRepository;

@Service
public class TaxDataService {
	
	@Autowired
	TaxDataRepository taxDataRepository;

	public Optional<TaxData> newTaxData(TaxData taxData) {
	    try {    
	        return Optional.ofNullable(this.taxDataRepository.save(taxData));
	    } catch (DataIntegrityViolationException | TransientPropertyValueException error ) {	        
	    	throw new DataIntegrityViolationException("Clave duplicada en taxData");
	    }
	}
	
}
