package com.bootcampASJ.tzappa.Services;

import java.util.Optional;

import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.ContactData;
import com.bootcampASJ.tzappa.Repositories.ContactDataRepository;

@Service
public class ContactDataService {

	@Autowired
	private ContactDataRepository contactDataRepository;

	public Optional<ContactData> newContactData(ContactData contactData) {
	    try {	    	
	        return Optional.ofNullable(this.contactDataRepository.save(contactData));
	    } catch (DataIntegrityViolationException | TransientPropertyValueException error) {	  
	    	throw new DataIntegrityViolationException("Clave duplicada en ContactData");
	    }
	}	
	
}
