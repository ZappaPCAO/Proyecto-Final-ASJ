package com.bootcampASJ.tzappa.utils;

import com.bootcampASJ.tzappa.Models.ContactData;
import com.bootcampASJ.tzappa.Models.Location;
import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Models.TaxData;

public  class dataValidation {
	
	public dataValidation(){}
	
	private Boolean checkSpecialCharacters(String data) {
		String regex = "^(?:[0-9 \\u00c0-\\u01ff a-zA-Z'_ .,@/:])+$";
		
		return data.matches(regex);
	}
	
	private Boolean checkOnlyNumbers(String data) {
		String regex = "^[0-9_.,]+$";
		
		return data.matches(regex);
	}
	
	private Boolean isCuit(String cuit) {
		String regex = "^\\d{2}-\\d{8}-\\d{1}$";

		return cuit.matches(regex);
	}
	private Boolean isWebsite(String website) {
		String regex = "^(https?|ftp):\\/\\/[^\\s/$.?#].[^\\s]*$";
		
		return website.matches(regex);
	}
	private Boolean isEmail(String email) {
		String regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
		
		return email.matches(regex);
	}
	private Boolean isPhone(String phone) {
		String regex = "^\\(\\d{3}\\) \\d{3}-\\d{4}$";
		
		return phone.matches(regex);
	}
	
	private Boolean validateLocation(Location location) {
		return this.checkSpecialCharacters(location.getStreet());		
	}
	
	private Boolean validateContactData(ContactData contactData) {
		if(!this.checkSpecialCharacters(contactData.getName()))
			return false;
		if(!this.checkSpecialCharacters(contactData.getLastName()))
			return false;
		if(!this.checkSpecialCharacters(contactData.getRole()))
			return false;
		if(!this.isEmail(contactData.getEmail()))
			return false;
		if(!this.isPhone(contactData.getPhone()))
			return false;
		
		return true;
	}
	
	private Boolean validateTaxData(TaxData taxData) {		
		return this.isCuit(taxData.getCuit());
	}
	
	public Boolean validateProvider(Provider provider) {
		
		// Requeridos
		if(!this.checkSpecialCharacters(provider.getBusinessName()))
			return false;
		if(!this.checkSpecialCharacters(provider.getCodProvider()))
			return false;
		if(!this.isEmail(provider.getEmail()))
			return false;
		if(!this.isPhone(provider.getPhone()))
			return false;
		if(!this.validateTaxData(provider.getTaxData()))
			return false;
        if(!this.validateLocation(provider.getLocation()))
        	return false;
        if(!this.validateContactData(provider.getContactData()))
        	return false;
       
        // No requeridos
        if(provider.getLogo() != null) {
	        if(!this.isWebsite(provider.getLogo()))
	        	return false;
        }
        if(provider.getWebsite() != null) {
        	if(!this.isWebsite(provider.getWebsite()))
        		return false;
        }
        
        return true;
    }
}
